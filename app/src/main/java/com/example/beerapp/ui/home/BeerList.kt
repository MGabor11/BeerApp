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
import androidx.compose.ui.unit.dp
import com.example.beerapp.model.Beer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Composable
fun BeerList(
    beerList: List<Beer>,
    isNextPageLoading: Boolean,
    onBeerSelected: (String) -> Unit,
    onNeedToLoadNewBeers: () -> Unit
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val isFirstItemNotVisibleFlow = snapshotFlow {
        listState.firstVisibleItemIndex
    }
        .map {
            it > 0
        }
        .distinctUntilChanged()

    val isListReachedTheEndFlow = snapshotFlow { listState.isScrolledToTheEnd(3) }
        .distinctUntilChanged()

    var showButton by remember { mutableStateOf(false) }

    LaunchedEffect(listState, isNextPageLoading) {
        combine(
            isListReachedTheEndFlow,
            isFirstItemNotVisibleFlow
        ) { isListReachedTheEnd, isFirstItemNotVisible ->
            isListReachedTheEnd to isFirstItemNotVisible
        }
            .collect { (isListReachedTheEnd, isFirstItemNotVisible) ->
                if (isListReachedTheEnd && !isNextPageLoading && listState.layoutInfo.totalItemsCount > 0) {
                    onNeedToLoadNewBeers()
                    showButton = false
                } else showButton = isFirstItemNotVisible && !isListReachedTheEnd
            }
    }

    Box {
        LazyColumn(state = listState) {
            items(beerList) { beer ->
                BeerItem(beer, onBeerSelected)
            }
        }

        if (showButton) {
            Button(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                onClick = {
                    coroutineScope.launch {
                        // Animate scroll to the first item
                        listState.animateScrollToItem(index = 0)
                    }
                }
            ) {
                Text("Scroll to the top")
            }
        }

        if (isNextPageLoading && !showButton) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            )
        }
    }
}

fun LazyListState.isScrolledToTheEnd(belowTheLastIndex: Int = 0) =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0 >=
            (layoutInfo.totalItemsCount - 1 - belowTheLastIndex)