package com.labfinal.data.network.dto.coin

import com.labfinal.data.local.entity.CoinEntity
import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    val id: String,
    val symbol: String? = null,
    val name: String? = null,
    val supply: String? = null,
    val maxSupply: String? = null,
    val marketCapUsd: String? = null,
    val priceUsd: String? = null,
    val changePercent24Hr: String? = null
)

fun CoinDto.toEntity(): CoinEntity {
    return CoinEntity(
        id = id,
        symbol = symbol,
        name = name,
        supply = supply,
        maxSupply = maxSupply,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}
