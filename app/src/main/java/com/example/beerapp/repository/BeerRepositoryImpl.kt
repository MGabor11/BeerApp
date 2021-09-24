package com.example.beerapp.repository

import com.example.beerapp.db.BeerDataModel
import com.example.beerapp.model.Beer
import com.example.beerapp.network.BeerRemoteService
import com.example.beerapp.store.BeerDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeerRepositoryImpl @Inject constructor(
    private val beerRemoteService: BeerRemoteService,
    private val beerDataStore: BeerDataStore
) : BeerRepository {

    override suspend fun fetchBeers() {
        val beerListNetworkResponse = beerRemoteService.fetchBeers()
        beerDataStore.saveBeers(beerListNetworkResponse.map {
            BeerDataModel(it.id, it.name, it.description, it.imageUrl)
        })
    }

    override fun getBeer(beerId: String) = beerDataStore.getBeer(beerId)
        .map {
            Beer(it.id, it.name, it.description, it.imageUrl)
        }
        .flowOn(Dispatchers.IO)

    override fun getBeers() = beerDataStore.getBeers()
        .map { list ->
            list.map { Beer(it.id, it.name, it.description, it.imageUrl) }
        }
        .flowOn(Dispatchers.IO)
}
