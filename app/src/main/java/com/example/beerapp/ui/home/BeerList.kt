package com.example.beerapp.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.beerapp.model.Beer

@Composable
fun BeerList(beerList: List<Beer>, onBeerSelected: (String) -> Unit) {
    LazyColumn {
        items(beerList) { beer ->
            BeerItem(beer, onBeerSelected)
        }
    }
}
