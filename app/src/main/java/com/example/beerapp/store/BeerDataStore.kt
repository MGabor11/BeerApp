package com.example.beerapp.store

import com.example.beerapp.db.BeerDataModel
import kotlinx.coroutines.flow.Flow

interface BeerDataStore {
    fun saveBeers(list: List<BeerDataModel>)
    suspend fun setSelectedBeerId(beerId: String)
    fun getSelectedBeerId(): Flow<String>
    fun getBeer(beerId: String): Flow<BeerDataModel>
    fun getBeers(): Flow<List<BeerDataModel>>
    fun hasBeers(): Flow<Boolean>
}
