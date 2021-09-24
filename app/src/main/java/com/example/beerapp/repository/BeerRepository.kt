package com.example.beerapp.repository

import com.example.beerapp.model.Beer
import kotlinx.coroutines.flow.Flow

interface BeerRepository {
    suspend fun fetchBeers()
    fun getBeer(beerId: String): Flow<Beer>
    fun getBeers(): Flow<List<Beer>>
}
