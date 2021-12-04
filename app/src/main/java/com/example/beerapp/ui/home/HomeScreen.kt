package com.example.beerapp.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.beerapp.util.collectAsStateInLifecycle

@Composable
fun HomeScreen(onBeerSelected: (String) -> Unit, viewModel: HomeViewModel = hiltViewModel()) {

    BeerList(
        beerList = viewModel.beerFlow,
        onBeerSelected = { beerId ->
            //viewModel.selectBeer(beerId = beerId)
            onBeerSelected.invoke(beerId)
        }
    )
}
