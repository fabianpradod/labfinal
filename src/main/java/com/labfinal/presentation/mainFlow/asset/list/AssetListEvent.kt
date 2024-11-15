package com.labfinal.presentation.mainFlow.asset.list

sealed interface AssetListEvent {
    data object Getdata: AssetListEvent
    data object RetryClick: AssetListEvent
}