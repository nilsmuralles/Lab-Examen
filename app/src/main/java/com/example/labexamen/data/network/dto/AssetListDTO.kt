package com.example.labexamen.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class AssetListDTO(
    val data: List<AssetDTO>
)
