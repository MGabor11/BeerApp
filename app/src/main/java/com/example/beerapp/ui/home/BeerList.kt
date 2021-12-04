package com.example.beerapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.beerapp.model.Beer
import kotlinx.coroutines.flow.Flow

@Composable
fun BeerList(
    beerList: Flow<PagingData<Beer>>,
    onBeerSelected: (String) -> Unit
) {
    val beerListItems: LazyPagingItems<Beer> = beerList.collectAsLazyPagingItems()

    Box {
        LazyColumn {
            items(beerListItems) { item ->
                item?.let {
                    BeerItem(item, onBeerSelected)
                }
            }

            beerListItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        //You can add modifier to manage load state when first time response page is loading
                    }
                    loadState.append is LoadState.Loading -> {
                        //You can add modifier to manage load state when next response page is loading
                    }
                    loadState.append is LoadState.Error -> {
                        //You can use modifier to show error message
                    }
                }
            }
        }
    }
}
