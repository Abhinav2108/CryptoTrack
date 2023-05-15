package com.example.cryptotrack.data.repository

import com.example.cryptotrack.data.source.CoinGeckoApi
import com.example.cryptotrack.data.source.dto.CoinDetailsDTO
import com.example.cryptotrack.data.source.dto.CoinListDTO
import com.example.cryptotrack.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val coinGeckoApi: CoinGeckoApi): CoinRepository {
    override suspend fun getAllCoins(page: String): CoinListDTO {
        return coinGeckoApi.getAllCoins(page)
    }

    override suspend fun getCoinsById(id: String): CoinDetailsDTO {
        return coinGeckoApi.getCoinsById(id)
    }
}