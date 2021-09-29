package com.example.beerapp.network

import com.example.beerapp.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BeerApiService {

    @GET("beers/{beerId}")
    suspend fun getBeer(@Path("beerId") beerId: String): Response<BeerDetailResponse>

    @GET("beers")
    suspend fun getBeersByPage(
        @Query("page") page: Int,
        @Query("per_page") resultPerPage: Int = Constants.BEER_PER_PAGE
    ): Response<List<BeerSimpleResponse>>
}
