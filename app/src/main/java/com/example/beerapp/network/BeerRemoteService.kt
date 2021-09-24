package com.example.beerapp.network

interface BeerRemoteService {
    suspend fun fetchBeers() :List<BeerResponse>
}
