package com.raino.helth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raino.helth.data.network.ApiService
import com.raino.helth.data.network.model.ResponseAvidHealth
import com.raino.helth.utiles.NetworkResult
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val apiService: ApiService
) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    init {
        Napier.d("MainViewModel initialized")
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                Napier.d("Fetching data from API")
                when (val response = apiService.getSampleData()) {
                    is NetworkResult.Error<ResponseAvidHealth> -> {
                        _state.value = _state.value.copy(
                            message = null,
                            isLoading = false,
                            error = response.error.message
                        )
                    }

                    is NetworkResult.Success<ResponseAvidHealth> -> {
                        _state.value = _state.value.copy(
                            message = response.data.toString(),
                            isLoading = false,
                            error = null
                        )
                    }
                }
            } catch (e: Exception) {
                Napier.e("Error fetching data", e)
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun updateMessage(message: String) {
        Napier.d("Updating message to: $message")
        _state.value = _state.value.copy(message = message)
    }
}

data class MainState(
    val message: String? = "Loading...",
    val isLoading: Boolean = true,
    val error: String? = null
) 