package com.labfinal.domain.network

import com.labfinal.data.model.Coin
import com.labfinal.domain.network.util.Result
import com.labfinal.data.network.dto.coin.CoinDto
import com.labfinal.data.network.dto.coin.CoinListDto
import com.labfinal.domain.network.util.NetworkError

interface CoinApi {
    suspend fun getAllCoins(): Result<CoinListDto, NetworkError>
    suspend fun getCoinById(id: Int): Result<CoinDto, NetworkError>
}