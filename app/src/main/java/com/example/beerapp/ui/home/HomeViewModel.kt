package com.example.beerapp.ui.home

import androidx.lifecycle.viewModelScope
import com.example.beerapp.repository.BeerRepository
import com.example.beerapp.ui.BaseViewModel
import com.example.beerapp.util.launchOnDefault
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val beerRepository: BeerRepository
) : BaseViewModel() {

    init {
        viewModelScope.launchOnDefault {
            isFullScreenLoading.value = true
            beerRepository.fetchBeers()
            isFullScreenLoading.value = false
        }
    }

    val beers = beerRepository.getBeers()
        .asStateFlow(emptyList())
}
