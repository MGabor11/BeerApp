package com.example.beerapp.network

interface BeerRemoteService {
    suspend fun fetchBeers(page: Int): List<BeerResponse>
}
