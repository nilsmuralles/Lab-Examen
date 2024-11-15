package com.example.labexamen.presentation.cryptolist

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object AssetListDestination

fun NavController.navigateToAssetListScreen(
    destination: AssetListDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.assetListScreen(
    onAssetClick: (String) -> Unit
) {
    composable<AssetListDestination> {
        AssetListRoute (
            onAssetClick = onAssetClick
        )
    }
}