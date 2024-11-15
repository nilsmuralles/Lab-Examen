package com.example.labexamen.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.labexamen.data.local.entity.AssetEntity
import com.example.labexamen.domain.model.Asset

@Dao
interface AssetDAO {
    @Query("SELECT * FROM AssetEntity")
    suspend fun getAllAssets(): List<AssetEntity>
    @Query("SELECT * FROM AssetEntity WHERE id = :assetID")
    suspend fun getAssetFromID(assetID: String): Asset
    @Upsert
    suspend fun insertAllAssets(assets: List<AssetEntity>)
}