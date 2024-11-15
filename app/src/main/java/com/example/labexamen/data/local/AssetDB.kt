package com.example.labexamen.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.labexamen.data.local.dao.AssetDAO
import com.example.labexamen.data.local.entity.AssetEntity

@Database(
    entities = [
        AssetEntity::class
    ],
    version = 4
)
abstract class AssetDB: RoomDatabase() {
    abstract fun assetDAO(): AssetDAO
}