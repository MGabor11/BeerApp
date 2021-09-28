package com.example.beerapp.network

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeerRemoteServiceImpl @Inject constructor(
    private val beerApiService: BeerApiService
) : BeerRemoteService {

    override suspend fun fetchBeers(page: Int): List<BeerResponse> =
        beerApiService.getBeersByPage(page).body() ?: emptyList()
}
