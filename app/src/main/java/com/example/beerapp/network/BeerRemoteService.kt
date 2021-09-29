package com.example.beerapp.network

interface BeerRemoteService {
    suspend fun getBeer(beerId: String): BeerDetailResponse
    suspend fun fetchBeers(page: Int): List<BeerSimpleResponse>
}
