package com.example.cryptotrack.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptotrack.domain.use_cases.GetCoinDetailsByIdUseCase
import com.example.cryptotrack.presentation.states.CoinDetailsState
import com.example.cryptotrack.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailsByIdViewModel @Inject constructor(private val coinDetailsByIdUseCase: GetCoinDetailsByIdUseCase): ViewModel() {

    private val coinValue = MutableStateFlow(CoinDetailsState())
    var _coinValue: StateFlow<CoinDetailsState> = coinValue

    fun getCoinById(id: String) = viewModelScope.launch(Dispatchers.IO) {
        coinDetailsByIdUseCase(id).collect{
            when(it){
                is ResponseState.Loading ->{
                    coinValue.value = CoinDetailsState(isLoading = true)
                }
                is ResponseState.Success ->{
                    coinValue.value = CoinDetailsState(coinDetails = it.data)
                }
                is ResponseState.Error ->{
                    coinValue.value = CoinDetailsState(errorMessage = it.message.toString())
                }
            }
        }
    }
}