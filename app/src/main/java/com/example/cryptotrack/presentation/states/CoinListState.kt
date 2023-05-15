package com.example.cryptotrack.presentation.states

import com.example.cryptotrack.domain.model.Coin
import com.example.cryptotrack.utils.ResponseState

data class CoinListState(
    val isLoading: Boolean = false,
    val coinList: List<Coin> = emptyList(),
    val errorMessage: String = ""
)
