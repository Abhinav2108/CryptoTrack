package com.example.cryptotrack.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptotrack.domain.use_cases.GetCoinsUseCase
import com.example.cryptotrack.presentation.states.CoinListState
import com.example.cryptotrack.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(private val coinListUseCase: GetCoinsUseCase): ViewModel() {

    private val coinListValue = MutableStateFlow(CoinListState())
    val _coinListValue: StateFlow<CoinListState> = coinListValue

    fun getAllCoins(page: String) = viewModelScope.launch(Dispatchers.IO) {
        coinListUseCase(page).collect{
            when(it){
                is ResponseState.Loading ->{
                    coinListValue.value = CoinListState(isLoading = true)
                }
                is ResponseState.Success ->{
                    coinListValue.value = CoinListState(coinList = it.data?: emptyList())
                }
                is ResponseState.Error ->{
                    coinListValue.value = CoinListState(errorMessage = it.message.toString())
                }
            }
        }
    }
}