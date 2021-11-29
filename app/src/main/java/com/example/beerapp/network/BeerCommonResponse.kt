package com.example.beerapp.network

import com.squareup.moshi.Json

interface BeerCommonResponse {
    val id: String
    val name: String
    val description: String
    val imageUrl: String
}