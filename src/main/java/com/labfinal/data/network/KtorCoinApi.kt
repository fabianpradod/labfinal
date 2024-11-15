package com.labfinal.data.network


import com.labfinal.data.network.dto.coin.CoinDto
import com.labfinal.domain.network.util.Result
import com.labfinal.data.network.dto.coin.CoinListDto
import com.labfinal.data.network.util.safeCall
import com.labfinal.domain.network.CoinApi
import com.labfinal.domain.network.util.NetworkError
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class KtorCoinApi(
    private val httpClient: HttpClient
) : CoinApi {
    override suspend fun getAllCoins(): Result<CoinListDto, NetworkError> {
        return safeCall {
            httpClient.get("https://api.coincap.io/v2/assets")
        }
    }
    override suspend fun getCoinById(id: Int): Result<CoinDto, NetworkError> {
        return safeCall {
            httpClient.get("https://api.coincap.io/v2/assets/${id}")
        }
    }
}
