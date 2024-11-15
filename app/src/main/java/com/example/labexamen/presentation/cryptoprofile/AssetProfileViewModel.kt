package com.example.labexamen.presentation.cryptoprofile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.labexamen.domain.network.util.onError
import com.example.labexamen.domain.network.util.onSuccess
import com.example.labexamen.domain.repository.AssetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AssetProfileViewModel(
    private val assetRepository: AssetRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val assetProfile = savedStateHandle.toRoute<AssetProfileDestination>()
    private val _uiState: MutableStateFlow<AssetProfileState> = MutableStateFlow(AssetProfileState())
    val uiState = _uiState.asStateFlow()

    init {
        getAssetData()
    }

    fun getAssetData() {
        viewModelScope.launch {
            viewModelScope.launch {
                val asset = assetRepository.getAssetByID(id = assetProfile.assetID)
                _uiState.update { state ->
                    state.copy(
                        data = asset
                    )
                }
            }
        }
    }

}