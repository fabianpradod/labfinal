package com.labfinal.data.repository

import com.labfinal.data.local.dao.CoinDao
import com.labfinal.data.local.entity.mapToModel
import com.labfinal.data.model.Coin
import com.labfinal.data.network.dto.coin.CoinListDto
import com.labfinal.data.network.dto.coin.toEntity
import com.labfinal.domain.CoinRepository
import com.labfinal.domain.network.CoinApi
import com.labfinal.domain.network.util.DataError
import com.labfinal.domain.network.util.NetworkError
import com.labfinal.domain.network.util.Result
import com.labfinal.domain.network.util.onError
import com.labfinal.domain.network.util.onSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

class LocalCoinRepository(
    private val coinDao: CoinDao,
    private val coinApi: CoinApi
) : CoinRepository {

    override suspend fun initialSync(): Result<List<Coin>, DataError> {
        delay(2000L)
        when (val result = coinApi.getAllCoins()) {
            is Result.Error -> {
                val localCoins = coinDao.getAllCoins()

                if (localCoins.isNullOrEmpty()) {

                    if (result.error == NetworkError.NO_INTERNET) {
                        return Result.Error(
                            DataError.NO_INTERNET
                        )
                    }
                    return Result.Error(
                        DataError.GENERIC_ERROR
                    )
                } else {
                    return Result.Success(
                        localCoins.map { it.mapToModel() }
                    )

                }
            }
            is Result.Success -> {
                val remoteCoins = result.data.data
                return Result.Success(
                    remoteCoins.map { it.toEntity().mapToModel() }
                )
            }
        }
    }

    override suspend fun getData(): Boolean {
        return try {
                coinApi.getAllCoins()
                    .onSuccess { characterListDto ->
                        val coinsToInsert = characterListDto.data.map { it.toEntity() }
                        coinDao.insertAll(coinsToInsert)
                    }
                    .onError { error ->
                        println("Error fetching coins: $error")
                        return false
                    }
            true
        } catch (e: Exception) {
            coroutineContext.ensureActive()
            println("Unexpected error during getData: ${e.message}")
            false
        }
    }

    override suspend fun getCoinById(id: Int): Result<Coin, DataError> {
        delay(2000L)
        when (val result = coinApi.getCoinById(id)) {
            is Result.Error -> {
                val localCoin = coinDao.getCoinById(id)

                if (localCoin == null) {

                    if (result.error == NetworkError.NO_INTERNET) {
                        return Result.Error(
                            DataError.NO_INTERNET
                        )
                    }
                    return Result.Error(
                        DataError.GENERIC_ERROR
                    )
                } else {
                    return Result.Success(
                        localCoin.mapToModel()
                    )
                }

            }
            is Result.Success -> {
                val remoteCoins = result.data
                return Result.Success(
                    remoteCoins.toEntity().mapToModel()
                )
            }
        }
    }

}