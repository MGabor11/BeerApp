package com.example.beerapp.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.beerapp.util.collectAsStateInLifecycle

@Composable
fun HomeScreen(onBeerSelected: (String) -> Unit, viewModel: HomeViewModel = hiltViewModel()) {

    val beerList by viewModel.beers.collectAsStateInLifecycle()

    BeerList(beerList = beerList, onBeerSelected = onBeerSelected)
}
