package com.example.beerapp.ui.detail

import com.example.beerapp.repository.BeerRepository
import com.example.beerapp.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val beerRepository: BeerRepository
) : BaseViewModel() {

    fun getBeer(id: String) = beerRepository.getBeer(id).asStateFlow()
}
