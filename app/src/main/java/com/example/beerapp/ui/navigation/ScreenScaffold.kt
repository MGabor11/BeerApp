package com.example.beerapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.getAll
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.example.beerapp.ui.BaseViewModel
import com.example.beerapp.util.requireActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun ScreenScaffold(
    navBackStackEntry: NavBackStackEntry,
    content: @Composable () -> Unit
) {
    content()

    val activity = LocalContext.current.requireActivity()
    val loaderViewModel: LoaderViewModel = viewModel(viewModelStoreOwner = activity)

    LaunchedEffect(Unit) {
        val viewModel = navBackStackEntry.viewModelStore.getAll()
            .filterIsInstance<BaseViewModel>()
            .firstOrNull()

        if (viewModel != null) {
            handleLoaderState(navBackStackEntry, loaderViewModel)
        }
    }
}

private fun CoroutineScope.handleLoaderState(
    navBackStackEntry: NavBackStackEntry,
    loaderViewModel: LoaderViewModel
) {
    navBackStackEntry.viewModelStore.getAll()
        .filterIsInstance<BaseViewModel>()
        .onEach {
            handleLoaderChanges(it.isFullScreenLoading, loaderViewModel.fullScreenLoader)
        }
}

private fun CoroutineScope.handleLoaderChanges(
    loaderChanges: Flow<Boolean>,
    loader: Loader
) {
    loaderChanges
        .onEach { showLoader ->
            if (showLoader) {
                loader.show()
            } else {
                loader.hide()
            }
        }
        .launchIn(this)
}
