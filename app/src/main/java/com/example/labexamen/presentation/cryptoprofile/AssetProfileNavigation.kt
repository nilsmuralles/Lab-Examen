package com.example.labexamen.presentation.cryptoprofile

import kotlinx.serialization.Serializable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.NavOptions

@Serializable
data class AssetProfileDestination(
    val assetID: String
)

fun NavController.navigateToAssetProfileScreen(
    assetID: String,
    navOptions: NavOptions? = null
){
    this.navigate(
        route = AssetProfileDestination(assetID = assetID),
        navOptions
    )
}

fun NavGraphBuilder.assetProfileScreen(){
    composable<AssetProfileDestination> {
            AssetProfileRoute()
    }
}