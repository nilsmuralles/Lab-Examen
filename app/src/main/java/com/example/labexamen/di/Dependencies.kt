package com.example.labexamen.di

import android.content.Context
import androidx.room.Room
import com.example.labexamen.data.local.AssetDB
import com.example.labexamen.data.network.HttpClientFactory
import io.ktor.client.HttpClient

object Dependencies {
    private var database: AssetDB? = null
    private var httpClient: HttpClient? = null

    private fun buildHttpClient(): HttpClient = HttpClientFactory.create()

    private fun buildDatabase(context: Context): AssetDB {
        return Room.databaseBuilder(
            context,
            AssetDB::class.java,
            "assets.db"
        ).build()
    }

    fun provideDataBase(context: Context): AssetDB {
        return database ?: synchronized(this) {
            database ?: buildDatabase(context).also { database = it }
        }
    }

    fun provideHttpClient(): HttpClient {
        return httpClient ?: synchronized(this) {
            httpClient ?: buildHttpClient().also { httpClient = it }
        }
    }
}