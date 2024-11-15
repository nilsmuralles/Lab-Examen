package com.example.labexamen.domain.network

import com.example.labexamen.data.network.dto.AssetListDTO
import com.example.labexamen.domain.network.util.NetworkError
import com.example.labexamen.domain.network.util.Result

interface CoinCapAPI {
    suspend fun getAllAssets(): Result<AssetListDTO, NetworkError>
}