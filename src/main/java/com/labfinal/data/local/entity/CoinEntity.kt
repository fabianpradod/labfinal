package com.labfinal.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.labfinal.data.model.Coin

@Entity
data class CoinEntity(
    @PrimaryKey val id: String,
    val symbol: String? = null,
    val name: String? = null,
    val supply: String? = null,
    val maxSupply: String? = null,
    val marketCapUsd: String? = null,
    val priceUsd: String? = null,
    val changePercent24Hr: String? = null
)

fun Coin.mapToEntity(): CoinEntity {
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

fun CoinEntity.mapToModel(): Coin {
    return Coin(
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