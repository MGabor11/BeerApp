package com.example.beerapp.model

data class BeerDetail(
    override val id: String,
    override val name: String,
    override val description: String,
    override val imageUrl: String,
    val ibu: Float,
    val ph: Float,
    val foodPairing: List<String>
) : BeerCommon