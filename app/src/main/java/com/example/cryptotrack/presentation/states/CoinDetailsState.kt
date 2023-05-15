package com.example.cryptotrack.presentation.states

import com.example.cryptotrack.domain.model.Coin
import com.example.cryptotrack.domain.model.CoinDetails

data class CoinDetailsState(
    val isLoading: Boolean = false,
    val coinDetails: CoinDetails?= null,
    val errorMessage: String = ""
)
