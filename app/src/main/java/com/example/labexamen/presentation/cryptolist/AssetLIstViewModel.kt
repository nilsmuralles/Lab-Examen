package com.example.labexamen.presentation.cryptolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.labexamen.data.network.KtorCoinCapAPI
import com.example.labexamen.data.repository.LocalAssetRepository
import com.example.labexamen.di.Dependencies
import com.example.labexamen.domain.network.util.onError
import com.example.labexamen.domain.network.util.onSuccess
import com.example.labexamen.domain.repository.AssetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AssetListViewModel(
    private val assetRepository: AssetRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<AssetListState> = MutableStateFlow(AssetListState())
    val uiState = _uiState.asStateFlow()

    init {
        getAssets()
    }

    fun getAssets() {
        viewModelScope.launch {
            assetRepository.getAllAssets()
                .onSuccess { assets ->
                    _uiState.update { state ->
                        state.copy(
                            data = assets
                        )
                    }
                }
                .onError {
                    _uiState.update { state ->
                        state.copy(
                            error = true
                        )
                    }
                }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val db = Dependencies.provideDataBase(application)
                val api = KtorCoinCapAPI(Dependencies.provideHttpClient())
                AssetListViewModel(
                    assetRepository = LocalAssetRepository(
                        assetDAO = db.assetDAO(),
                        coinCapAPI = api
                    ),
                )
            }
        }
    }
}