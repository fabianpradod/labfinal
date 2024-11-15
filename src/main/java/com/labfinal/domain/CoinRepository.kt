package com.labfinal.domain

import com.labfinal.data.model.Coin
import com.labfinal.domain.network.util.DataError
import com.labfinal.domain.network.util.Result

interface CoinRepository {
    suspend fun initialSync(): Result<List<Coin>, DataError>
    suspend fun getData(): Boolean
    suspend fun getCoinById(id: Int): Result<Coin, DataError>
}