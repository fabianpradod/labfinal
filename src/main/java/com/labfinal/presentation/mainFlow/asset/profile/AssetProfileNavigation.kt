package com.labfinal.presentation.mainFlow.asset.profile

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data class AssetProfileDestination(
    val id: String
)


fun NavController.navigateToAssetProfileScreen(
    destination: AssetProfileDestination,
    navOptions: NavOptions? = null
) {
    this.navigate(destination, navOptions)
}