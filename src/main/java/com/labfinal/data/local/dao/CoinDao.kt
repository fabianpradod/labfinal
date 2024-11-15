package com.labfinal.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.labfinal.data.local.entity.CoinEntity

@Dao
interface CoinDao {
    @Insert
    suspend fun insertAll(coins: List<CoinEntity>)

    @Query ("SELECT * FROM CoinEntity")
    suspend fun getAllCoins(): List<CoinEntity>?

    @Query ("SELECT * FROM CoinEntity WHERE id = :id")
    suspend fun getCoinById(id: Int): CoinEntity?
}