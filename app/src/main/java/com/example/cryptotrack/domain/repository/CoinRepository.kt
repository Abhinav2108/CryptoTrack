package com.example.cryptotrack.domain.repository

import com.example.cryptotrack.data.source.dto.CoinDetailsDTO
import com.example.cryptotrack.data.source.dto.CoinListDTO

interface CoinRepository {

    suspend fun getAllCoins(page:String):CoinListDTO

    suspend fun getCoinsById(id:String):CoinDetailsDTO
}