package com.example.labexamen.domain.repository

import com.example.labexamen.domain.model.Asset
import com.example.labexamen.domain.model.DataError
import com.example.labexamen.domain.network.util.Result

interface AssetRepository {
    suspend fun getAllAssets(): Result<List<Asset>, DataError>
    suspend fun getAssetByID(id: String): Asset
}