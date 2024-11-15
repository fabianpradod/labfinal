package com.labfinal.presentation.mainFlow.asset.list

import com.labfinal.data.model.Coin

data class AssetListState(
    val coins: List<Coin> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false
)