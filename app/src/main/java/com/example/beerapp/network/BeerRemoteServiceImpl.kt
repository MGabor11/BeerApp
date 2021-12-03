package com.example.beerapp.network

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeerRemoteServiceImpl @Inject constructor(
    private val beerApiService: BeerApiService
) : BeerRemoteService {

    override suspend fun getBeer(beerId: String): BeerDetailResponse =
        beerApiService.getBeer(beerId).body()?.first()
            ?: BeerDetailResponse("", "", "", "", 0f, 0f, emptyList())

    override suspend fun fetchBeers(page: Int): List<BeerSimpleResponse> =
        beerApiService.getBeersByPage(page).body() ?: emptyList()

    override suspend fun getBeersByPageForPaging(page: Int): List<BeerSimpleResponse> {

    }
}
