package com.example.labexamen.data.network.dto

import com.example.labexamen.data.local.entity.AssetEntity
import com.example.labexamen.domain.model.Asset
import kotlinx.serialization.Serializable

@Serializable
data class AssetDTO(
    val id: String,
    val name: String,
    val symbol: String,
    val priceUsd: String,
    val changePercent24Hr: String,
    val supply: String,
    val maxSupply: String?,
    val marketCapUsd: String
)

fun AssetDTO.mapToModel(): Asset {
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

fun AssetDTO.mapToEntity(): AssetEntity {
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