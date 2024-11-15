package com.labfinal.data.model

data class Coin(
    val id: String,
    val symbol: String? = null,
    val name: String? = null,
    val supply: String? = null,
    val maxSupply: String? = null,
    val marketCapUsd: String? = null,
    val priceUsd: String? = null,
    val changePercent24Hr: String? = null
)

