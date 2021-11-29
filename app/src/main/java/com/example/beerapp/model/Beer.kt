package com.example.beerapp.model

data class Beer(
    override val id: String,
    override val name: String,
    override val description: String,
    override val imageUrl: String
) : BeerCommon
