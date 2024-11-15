package com.labfinal.data.network.dto.coin


import kotlinx.serialization.Serializable

@Serializable
data class CoinListDto(
    val data: List<CoinDto>
)