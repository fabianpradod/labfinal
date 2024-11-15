package com.labfinal.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.labfinal.data.local.dao.CoinDao
import com.labfinal.data.local.entity.CoinEntity


@Database(
    entities = [
        CoinEntity::class,
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun coinDao(): CoinDao
}
