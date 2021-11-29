package com.example.beerapp.repository

import com.example.beerapp.model.Beer
import com.example.beerapp.model.BeerDetail
import kotlinx.coroutines.flow.Flow

interface BeerRepository {
    suspend fun fetchBeers(page: Int)
    suspend fun setSelectedBeerId(beerId: String)
    fun getBeer(beerId: String): Flow<Beer>
    fun getSelectedBeer(): Flow<Beer>
    fun getBeerDetailedInfo(beerId: String): Flow<BeerDetail>
    fun getBeers(): Flow<List<Beer>>
    fun hasBeers(): Flow<Boolean>
}
