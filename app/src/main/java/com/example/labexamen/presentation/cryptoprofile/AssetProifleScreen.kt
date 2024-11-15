package com.example.labexamen.presentation.cryptoprofile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.labexamen.domain.model.Asset

@Composable
fun AssetProfileRoute(
    viewModel: AssetProfileViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    AssetProfileContent(
        state = state
    )
}

@Composable
private fun AssetProfileContent(
    state: AssetProfileState
) {
    Column {
        AssetProfile(
            asset = state.data,
            modifier = Modifier.fillMaxSize()
                .padding(25.dp)
        )
    }
}

@Composable
private fun AssetProfile(
    asset: Asset?,
    modifier: Modifier
) {
    if (asset != null) {
        Row (
            modifier = modifier,
            horizontalArrangement = Arrangement.Center
        ){
            Column (
                verticalArrangement = Arrangement.spacedBy(25.dp),
                modifier = Modifier.fillMaxWidth()
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = asset.name,
                        textAlign = TextAlign.Center
                    )
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ){

                    Text(
                        text = asset.symbol
                    )
                    Text(
                        text = asset.supply
                    )
                    Text(
                        text = asset.marketCapUsd
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
                    if (asset.maxSupply != null) {
                        Text(
                            text = asset.maxSupply
                        )
                    } else {
                        Text(
                            text = "No max supply"
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun AssetScreenPreview() {
    AssetProfileContent(
        state = AssetProfileState(
            data = Asset(
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
}



