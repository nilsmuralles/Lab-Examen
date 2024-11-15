package com.example.labexamen.data.network

import com.example.labexamen.data.network.dto.AssetListDTO
import com.example.labexamen.data.network.util.safeCall
import com.example.labexamen.domain.network.CoinCapAPI
import com.example.labexamen.domain.network.util.NetworkError
import com.example.labexamen.domain.network.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class KtorCoinCapAPI(
    private val httpClient: HttpClient
): CoinCapAPI {
    override suspend fun getAllAssets(): Result<AssetListDTO, NetworkError> {
        return safeCall<AssetListDTO> {
            httpClient.get("https://api.coincap.io/v2/assets")
        }
    }
}