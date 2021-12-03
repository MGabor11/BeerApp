package com.example.beerapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.beerapp.R
import com.example.beerapp.model.Beer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Composable
fun BeerList(
    beerList: List<Beer>,
    onBeerSelected: (String) -> Unit
) {
    Box {
        LazyColumn {
            items(beerList) { beer ->
                BeerItem(beer, onBeerSelected)
            }
        }
    }
}

/*
fun LazyListState.isScrolledToTheEnd(belowTheLastIndex: Int = 0) =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0 >=
            (layoutInfo.totalItemsCount - 1 - belowTheLastIndex)*/
