package com.example.labexamen.di

import com.example.labexamen.data.network.HttpClientFactory
import io.ktor.client.HttpClient

object Dependencies {
    private var httpClient: HttpClient? = null

    private fun buildHttpClient(): HttpClient = HttpClientFactory.create()

    fun provideHttpClient(): HttpClient {
        return httpClient ?: synchronized(this) {
            httpClient ?: buildHttpClient().also { httpClient = it }
        }
    }
}