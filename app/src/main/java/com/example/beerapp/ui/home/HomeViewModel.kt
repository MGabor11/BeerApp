package com.example.beerapp.ui.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.beerapp.Constants
import com.example.beerapp.model.Beer
import com.example.beerapp.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val beerSource: BeerSource
) : BaseViewModel() {

    val beerFlow : Flow<PagingData<Beer>> = Pager(PagingConfig(Constants.BEER_PER_PAGE)) {
        beerSource
    }.flow

    /*val beers = beerFlow.asStateFlow(null)*/

    /*private val beerFlow = beerRepository.getBeers()
    private val beerCountFlow = beerFlow.map { it.size }
    private val currentPage = beerCountFlow.map {
        (it.toDouble() / Constants.BEER_PER_PAGE).toInt()
    }

    private var hasMoreBeer = true

    val beers = beerFlow.asStateFlow(emptyList())

    var isNextPageLoading = MutableStateFlow(false)

    init {
        viewModelScope.launchOnDefault {
            isFullScreenLoading.value = true
            val hasBeers = beerRepository.hasBeers().first()
            if (!hasBeers) {
                beerRepository.fetchBeers(page = getNextPage())
            }
            isFullScreenLoading.value = false
        }
    }

    fun fetchBeers() = viewModelScope.launchOnDefault {
        if (hasMoreBeer) {
            isNextPageLoading.value = true
            val nextBeerPage = getNextPage()
            beerRepository.fetchBeers(page = nextBeerPage)
            isNextPageLoading.value = false

            if (getNextPage() == nextBeerPage) {
                hasMoreBeer = false
            }
        }
    }

    fun selectBeer(beerId: String) = viewModelScope.launchOnDefault {
        beerRepository.setSelectedBeerId(beerId = beerId)
    }

    private suspend fun getNextPage() = currentPage.first() + 1*/
}
