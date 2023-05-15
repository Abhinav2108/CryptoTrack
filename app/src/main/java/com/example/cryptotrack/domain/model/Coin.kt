package com.example.cryptotrack.domain.model

data class Coin(
    val id: String,
    val image: String,
    val name: String,
    val price_change_24h: Double,
    val price_change_percentage_24h: Double,
    val high_24h: Double,
    val low_24h: Double
)
