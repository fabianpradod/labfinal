package com.labfinal.presentation.mainFlow.asset.list

import com.labfinal.data.repository.LocalCoinRepository
import com.labfinal.domain.CoinRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.labfinal.data.network.KtorCoinApi
import com.labfinal.di.AppDependencies
import com.labfinal.di.KtorDependencies
import com.labfinal.domain.network.util.onError
import com.labfinal.domain.network.util.onSuccess
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AssetListViewModel(
    private val coinRepository: CoinRepository
) : ViewModel() {

    private var getDataJob: Job? = null
    private val _state = MutableStateFlow(AssetListState())
    val state = _state.asStateFlow()

    init {
        syncCoins()
    }

    fun onEvent(event: AssetListEvent) {
        when (event) {
            AssetListEvent.Getdata -> {
                getData()
            }
            AssetListEvent.RetryClick -> {
                syncCoins()
            }
        }
    }

    private fun syncCoins() {
        getDataJob = viewModelScope.launch {
            _state.update { it.copy(isLoading = true, isError = false) }
            coinRepository
                .initialSync()
                .onSuccess { coins ->
                    _state.update { it.copy(
                        isLoading = false,
                        coins = coins
                        )
                    }
                }
                .onError { error ->
                    _state.update { it.copy(
                        isError = true,
                        isLoading = false
                    ) }
                }
        }
    }

    private fun getData() {
        TODO("Not yet implemented")
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val context = checkNotNull(this[APPLICATION_KEY])
                val appDatabase = AppDependencies.provideDatabase(context)
                val api = KtorCoinApi(KtorDependencies.provideHttpClient())
                AssetListViewModel(
                    coinRepository = LocalCoinRepository(
                        coinDao = appDatabase.coinDao(),
                        coinApi = api
                    )
                )
            }
        }
    }
}