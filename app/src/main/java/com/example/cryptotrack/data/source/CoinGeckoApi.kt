package com.example.cryptotrack.data.source

import com.example.cryptotrack.data.source.dto.CoinDetailsDTO
import com.example.cryptotrack.data.source.dto.CoinListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoApi {

    @GET("/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&sparkline=false&locale=en")
    suspend fun getAllCoins(@Query("page")page: String):CoinListDTO

    @GET("/api/v3/coins/{id}")
    suspend fun getCoinsById(@Path("id")id:String): CoinDetailsDTO
}