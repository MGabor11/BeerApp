package com.example.beerapp.ui.home

import androidx.lifecycle.viewModelScope
import com.example.beerapp.Constants
import com.example.beerapp.repository.BeerRepository
import com.example.beerapp.ui.BaseViewModel
import com.example.beerapp.util.launchOnDefault
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val beerRepository: BeerRepository
) : BaseViewModel() {

    private val beerFlow = beerRepository.getBeers()
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

    private suspend fun getNextPage() = currentPage.first() + 1
}
