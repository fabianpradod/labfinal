package com.labfinal.presentation.mainFlow.asset.list

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.labfinal.data.model.Coin
import com.labfinal.presentation.common.ErrorView
import com.labfinal.presentation.common.LoadingView
import com.labfinal.ui.theme.LabFinalTheme

@Composable
fun AssetListRoute(
    onCoinClick: (String) -> Unit,
    viewModel: AssetListViewModel = viewModel(factory = AssetListViewModel.Factory)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    AssetListScreen(
        state = state,
        onRetryClick = {},
        onGetDataClick = { viewModel.onEvent(AssetListEvent.Getdata) },
        onCoinClick = onCoinClick)
}

@Composable
private fun AssetListScreen(
    state: AssetListState,
    onRetryClick: () -> Unit,
    onGetDataClick: () -> Unit,
    onCoinClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        when {
            state.isLoading -> {
                LoadingView(
                    loadingText = "Obteniendo coins",
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            state.isError -> {
                ErrorView(
                    errorText = "Uh, oh. Error al obtener coins",
                    onRetryClick = onRetryClick,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            else -> {
                LazyColumn(
                    modifier = modifier
                ) {
                    items(state.coins) { item ->
                        AssetItem(
                            coin = item,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onCoinClick(item.id) }
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun AssetItem(
    coin: Coin,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column {
            Text(
                text = "${coin.name} - (${coin.symbol})",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Precio en dolares: ${coin.priceUsd}",
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = "Cambio porcentual en ultimas 24h: ${coin.changePercent24Hr}",
                style = MaterialTheme.typography.labelSmall,
//                color = if (coin.changePercent24Hr >= 0) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.error
            )
        }
    }

}


//
//
//@Preview
//@Composable
//private fun PreviewAssetListScreen() {
//    LabFinalTheme {
//        Surface {
//            ArticuloScreen(
//                coin = Noticia(
//                    type = CategoryType.Lugares,
//                    id = 1,
//                    name = "Place 1",
//                    smallText = "Brief description of Place 1",
//                    actualText1 = "Detailed information about Place 1",
//                    image = "tikal1",
//                    actualText2 = ""
//                ),
//                onArticuloBackClick = { /* Dummy back action for preview */ },
//                modifier = Modifier.fillMaxSize()
//            )
//        }
//    }
//}

