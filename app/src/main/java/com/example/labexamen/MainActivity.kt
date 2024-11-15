package com.example.labexamen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.labexamen.presentation.cryptolist.AssetListDestination
import com.example.labexamen.presentation.cryptolist.assetListScreen
import com.example.labexamen.presentation.cryptoprofile.assetProfileScreen
import com.example.labexamen.presentation.cryptoprofile.navigateToAssetProfileScreen
import com.example.labexamen.ui.theme.LabExamenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LabExamenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainNavigation(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
private fun MainNavigation(
    navHostController: NavHostController = rememberNavController(),
    modifier: Modifier
) {
    NavHost(
        navController = rememberNavController(),
        startDestination = AssetListDestination,
        modifier = Modifier
    ) {

        assetListScreen(
            onAssetClick =  { id ->
                navHostController.navigateToAssetProfileScreen(assetID = id)
            }
        )
        assetProfileScreen()
    }
}