package com.example.cryptotrack.data.source.dto

import com.example.cryptotrack.domain.model.CoinDetails

data class CoinDetailsDTO(
    val additional_notices: List<Any>,
    val asset_platform_id: Any,
    val block_time_in_minutes: Double,
    val categories: List<String>,
    val coingecko_rank: Double,
    val coingecko_score: Double,
    val community_data: CommunityData,
    val community_score: Double,
    val country_origin: String,
    val description: Description,
    val detail_platforms: DetailPlatforms,
    val developer_data: DeveloperData,
    val developer_score: Double,
    val genesis_date: String,
    val hashing_algorithm: String,
    val id: String,
    val image: Image,
    val last_updated: String,
    val links: Links,
    val liquidity_score: Double,
    val localization: Localization,
    val market_cap_rank: Double,
    val market_data: MarketData,
    val name: String,
    val platforms: Platforms,
    val public_interest_score: Double,
    val public_interest_stats: PublicInterestStats,
    val public_notice: Any,
    val sentiment_votes_down_percentage: Double,
    val sentiment_votes_up_percentage: Double,
    val status_updates: List<Any>,
    val symbol: String,
    val tickers: List<Ticker>,
    val watchlist_portfolio_users: Double
){
    fun toCoinDetails(): CoinDetails{
        return CoinDetails(
            id = id,
            image = image.large,
            name = name,
            price_change_24h = market_data.price_change_24h,
            price_change_percentage_24h = market_data.price_change_percentage_24h,
            high_24h = market_data.high_24h.usd.toDouble(),
            low_24h = market_data.low_24h.usd.toDouble(),
            description = description.en
        )
    }
}