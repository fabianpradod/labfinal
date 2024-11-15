package com.labfinal

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
import com.labfinal.ui.theme.LabFinalTheme
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import com.labfinal.presentation.mainFlow.asset.list.AssetListDestination
import com.labfinal.presentation.mainFlow.asset.list.assetListScreen
import com.labfinal.presentation.mainFlow.asset.profile.AssetProfileDestination
import com.labfinal.presentation.mainFlow.asset.profile.navigateToAssetProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabFinalTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = AssetListDestination,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        assetListScreen  (
                            onCoinClick = { coin ->
                                navController.navigateToAssetProfileScreen(
                                    destination = AssetProfileDestination(
                                        id = coin
                                    )
                                )
                            }
                        )

                    }
                }
            }
        }
    }
}
