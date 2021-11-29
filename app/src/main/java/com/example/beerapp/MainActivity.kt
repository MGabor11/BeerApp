package com.example.beerapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.beerapp.ui.detail.DetailScreen
import com.example.beerapp.ui.home.HomeScreen
import com.example.beerapp.ui.navigation.LoaderState
import com.example.beerapp.ui.navigation.LoaderViewModel
import com.example.beerapp.ui.navigation.LoadingScreen
import com.example.beerapp.ui.navigation.composableWithLoading
import com.example.beerapp.ui.theme.BeerAppTheme
import com.example.beerapp.ui.theme.Teal200
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loaderViewModel: LoaderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BeerAppTheme {
                ProvideWindowInsets {
                    val navController = rememberNavController()

                    var currentBeerSelection by remember { mutableStateOf("") }
                    var toolbarTitle by remember { mutableStateOf("") }
                    var showNavBackArrow by remember { mutableStateOf(false) }

                    Scaffold(topBar = {
                        TopAppBar(
                            title = { Text(toolbarTitle) },
                            navigationIcon = {
                                if (showNavBackArrow) {
                                    IconButton(onClick = { navController.navigateUp() }) {
                                        Icon(
                                            imageVector = Icons.Filled.ArrowBack,
                                            contentDescription = null
                                        )
                                    }
                                }
                            },
                            backgroundColor = Teal200
                        )
                    })
                    {
                        NavHost(
                            navController = navController,
                            startDestination = HOME_ROUTE,
                        ) {
                            composableWithLoading(route = HOME_ROUTE) {
                                toolbarTitle = stringResource(R.string.home_page)
                                showNavBackArrow = false

                                HomeScreen(
                                    onBeerSelected = { beerId ->
                                        navController.navigate("${BEER_DETAIL_ROUTE}/$beerId")
                                        currentBeerSelection = ""
                                        toolbarTitle = ""
                                    }
                                )
                            }
                            composableWithLoading(
                                route = "${BEER_DETAIL_ROUTE}/{$BEER_ID_KEY}",
                                arguments = listOf(navArgument(BEER_ID_KEY) {
                                    type = NavType.StringType
                                })
                            ) { backStackEntry ->
                                showNavBackArrow = true
                                toolbarTitle = currentBeerSelection

                                val arguments = requireNotNull(backStackEntry.arguments)
                                val beerId = arguments.getString(BEER_ID_KEY) ?: ""
                                DetailScreen(
                                    beerId = beerId,
                                    onToolbarTitleFetched = { title ->
                                        currentBeerSelection = title
                                    }
                                )
                            }
                        }
                    }

                    // TODO .collectAsStateInLifecycle() ?
                    val loaderState by loaderViewModel.fullScreenLoader.state.collectAsState()

                    if (loaderState == LoaderState.SHOW) {
                        LoadingScreen()
                    }
                }
            }
        }
    }

    companion object {
        private const val HOME_ROUTE = "home"
        private const val BEER_DETAIL_ROUTE = "beer"
        private const val BEER_ID_KEY = "beerId"
    }
}
