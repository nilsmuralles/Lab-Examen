package com.example.labexamen.presentation.cryptolist

import com.example.labexamen.domain.model.Asset

data class AssetListState(
    val data: List<Asset>? = null,
    val error: Boolean? = false
)
