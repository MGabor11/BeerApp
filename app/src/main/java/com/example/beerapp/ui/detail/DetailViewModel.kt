package com.example.beerapp.ui.detail

import com.example.beerapp.repository.BeerRepository
import com.example.beerapp.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val beerRepository: BeerRepository
) : BaseViewModel() {

    init {
        isFullScreenLoading.value = true
    }

    val selectedBeerName = beerRepository.getSelectedBeer()
        .map { it.name }
        .asStateFlow(null)

    fun getBeerDetailedInfo(beerId: String) = beerRepository.getBeerDetailedInfo(beerId)
        .onEach {
            isFullScreenLoading.value = false
        }.asStateFlow(null)
}
