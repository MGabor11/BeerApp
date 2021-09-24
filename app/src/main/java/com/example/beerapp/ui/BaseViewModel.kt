package com.example.beerapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.plus

abstract class BaseViewModel : ViewModel() {

    val isFullScreenLoading = MutableStateFlow(false)

    protected fun <T> Flow<T>.asStateFlow(): StateFlow<T?> = this.asStateFlow(null)

    protected fun <T> Flow<T>.asStateFlow(initialValue: T): StateFlow<T> =
        stateIn(viewModelScope + Dispatchers.Default, SharingStarted.WhileSubscribed(), initialValue)
}
