package com.example.beerapp.network

interface BeerRemoteService {
    suspend fun getBeer(beerId: String): BeerDetailResponse
    suspend fun fetchBeers(page: Int): List<BeerSimpleResponse>
    suspend fun getBeersByPageForPaging(page: Int): List<BeerSimpleResponse>
}
