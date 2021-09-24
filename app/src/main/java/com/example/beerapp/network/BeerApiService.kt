package com.example.beerapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BeerApiService {

    @GET("beers/{beerId}")
    suspend fun getBeer(@Path("beerId") beerId: String): Response<BeerResponse>

    @GET("beers")
    suspend fun getBeers(): Response<List<BeerResponse>>
}
