package com.example.beerapp.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BeerDetailResponse(
    @field:Json(name = "id") override val id: String,
    @field:Json(name = "name") override val name: String,
    @field:Json(name = "description") override val description: String,
    @field:Json(name = "image_url") override val imageUrl: String,
    @field:Json(name = "ibu") val ibu: Float,
    @field:Json(name = "ph") val ph: Float,
    @field:Json(name = "food_pairing") val foodPairing: List<String>
) : BeerCommonResponse
