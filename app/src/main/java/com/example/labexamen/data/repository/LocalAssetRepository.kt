package com.example.labexamen.data.repository

import com.example.labexamen.data.local.dao.AssetDAO
import com.example.labexamen.data.local.entity.mapToModel
import com.example.labexamen.data.network.dto.mapToEntity
import com.example.labexamen.data.network.dto.mapToModel
import com.example.labexamen.domain.model.Asset
import com.example.labexamen.domain.model.DataError
import com.example.labexamen.domain.network.CoinCapAPI
import com.example.labexamen.domain.network.util.NetworkError
import com.example.labexamen.domain.network.util.Result
import com.example.labexamen.domain.repository.AssetRepository

class LocalAssetRepository(
    private val assetDAO: AssetDAO,
    private val coinCapAPI: CoinCapAPI
): AssetRepository {
    override suspend fun getAllAssets(): Result<List<Asset>, DataError> {
        when (val result = coinCapAPI.getAllAssets()) {
            is Result.Error -> {
                val localAssets = assetDAO.getAllAssets()
                if (localAssets.isEmpty()) {
                    if (result.error == NetworkError.NO_INTERNET) {
                        return Result.Error(
                            DataError.NO_INTERNET
                        )
                    } else {
                        return Result.Error(DataError.GENERIC_ERROR)
                    }
                } else {
                    return Result.Success(
                        localAssets.map { it.mapToModel() }
                    )
                }
            }
            is Result.Success -> {
                val remoteAssets = result.data.data
                assetDAO.insertAllAssets(
                    remoteAssets.map { it.mapToEntity() }
                )
                return Result.Success(
                    remoteAssets.map { it.mapToModel() }
                )
            }
        }
    }

    override suspend fun getAssetByID(id: String): Asset {
        return assetDAO.getAssetFromID(assetID = id)
    }
}