package com.example.labexamen.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.labexamen.domain.model.Asset

@Entity
data class AssetEntity(
    @PrimaryKey val id: String,
    val name: String,
    val symbol: String,
    val priceUsd: String,
    val changePercent24Hr: String,
    val supply: String,
    val maxSupply: String?,
    val marketCapUsd: String
)

fun AssetEntity.mapToModel(): Asset {
    return Asset(
        id = id,
        name = name,
        symbol = symbol,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr,
        supply = supply,
        maxSupply = maxSupply,
        marketCapUsd = marketCapUsd
    )
}

fun Asset.mapToEntity(): AssetEntity {
    return AssetEntity(
        id = id,
        name = name,
        symbol = symbol,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr,
        supply = supply,
        maxSupply = maxSupply,
        marketCapUsd = marketCapUsd
    )
}