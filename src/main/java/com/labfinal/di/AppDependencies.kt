package com.labfinal.di

import android.content.Context
import com.labfinal.data.local.AppDatabase
import com.labfinal.data.local.AppDatabaseFactory

object AppDependencies {
    private var database: AppDatabase? = null

    fun provideDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this) {
            database ?: AppDatabaseFactory.create(context).also { database = it }
        }
    }
}