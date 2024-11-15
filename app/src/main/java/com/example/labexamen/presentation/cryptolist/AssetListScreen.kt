package com.example.labexamen.presentation.cryptolist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.material3.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.labexamen.domain.model.Asset
import com.example.labexamen.presentation.common.CustomTopBar
import com.example.labexamen.ui.theme.LabExamenTheme

@Composable
fun AssetListRoute(
    viewModel: AssetListViewModel = viewModel(factory = AssetListViewModel.Factory),
    onAssetClick: (String) -> Unit
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    AssetListScreen(
        state = state.value,
        onAssetClick = onAssetClick
    )
}

@Composable
private fun AssetListScreen(
    state: AssetListState,
    onAssetClick: (String) -> Unit
) {
    Column {
        CustomTopBar(
            title = "Criptomonedas",
            hasBack = false
        )
        AssetListContent(
            assets = state.data,
            modifier = Modifier.fillMaxWidth(),
            onAssetClick = onAssetClick
        )
    }
}

@Composable
private fun AssetListContent(
    assets: List<Asset>?,
    onAssetClick: (String) -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier
    ) {
        if (assets != null) {
            Column {
                assets.forEach { asset ->
                    AssetElement(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(25.dp)
                            .clickable { onAssetClick(asset.id) },
                        asset = asset
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
private fun AssetElement(
    modifier: Modifier,
    asset: Asset
){
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ){
        Column (
            verticalArrangement = Arrangement.spacedBy(25.dp),
            modifier = Modifier.fillMaxWidth()
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Text(
                    text = asset.name
                )
                Text(
                    text = asset.symbol
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = asset.priceUsd
                )
                Text(
                    text = asset.changePercent24Hr
                )
            }
        }
    }
}

@Preview
@Composable
private fun AssetListPreview() {
    LabExamenTheme {
        Surface (modifier = Modifier.fillMaxSize()){
            AssetListScreen(
                onAssetClick = {},
                state = AssetListState(
                    data = listOf(
                        Asset(
                            id = "1",
                            name = "BitCoin",
                            priceUsd = "5151514",
                            changePercent24Hr = "soadfjasoidf",
                            supply = "asdffs",
                            maxSupply = "asdfasdf",
                            marketCapUsd = "asdfasdf",
                            symbol = "sadofjsidf"
                        ),
                        Asset(
                            id = "1",
                            name = "BitCoin",
                            priceUsd = "5151514",
                            changePercent24Hr = "soadfjasoidf",
                            supply = "asdffs",
                            maxSupply = "asdfasdf",
                            marketCapUsd = "asdfasdf",
                            symbol = "sadofjsidf"
                        )
                    )
                )
            )
        }
    }
}