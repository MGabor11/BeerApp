package com.example.beerapp.repository

import com.example.beerapp.db.BeerDataModel
import com.example.beerapp.model.Beer
import com.example.beerapp.model.BeerDetail
import com.example.beerapp.network.BeerRemoteService
import com.example.beerapp.store.BeerDataStore
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

@Singleton
class BeerRepositoryImpl @Inject constructor(
    private val beerRemoteService: BeerRemoteService,
    private val beerDataStore: BeerDataStore
) : BeerRepository {

    override suspend fun fetchBeers(page: Int) {
        val beerListNetworkResponse = beerRemoteService.fetchBeers(page)
        beerDataStore.saveBeers(beerListNetworkResponse.map {
            BeerDataModel(it.id, it.name, it.description, it.imageUrl)
        })
    }

    override suspend fun setSelectedBeerId(beerId: String) = beerDataStore.setSelectedBeerId(beerId)

    override fun getBeer(beerId: String) = beerDataStore.getBeer(beerId)
        .map {
            Beer(it.id, it.name, it.description, it.imageUrl)
        }
        .flowOn(Dispatchers.IO)

    override fun getSelectedBeer() = beerDataStore.getSelectedBeerId()
        .filter { it.isNotEmpty() }
        .flatMapLatest { beerId ->
            getBeer(beerId = beerId)
        }.flowOn(Dispatchers.IO)

    override fun getBeerDetailedInfo(beerId: String) = flow {
        val beerResponse = beerRemoteService.getBeer(beerId)
        val beer = BeerDetail(
            beerResponse.id,
            beerResponse.name,
            beerResponse.description,
            beerResponse.imageUrl,
            beerResponse.ibu,
            beerResponse.ph,
            beerResponse.foodPairing
        )

        emit(beer)
    }.flowOn(Dispatchers.IO)

    override fun getBeers() = beerDataStore.getBeers()
        .map { list ->
            list.map { Beer(it.id, it.name, it.description, it.imageUrl) }
        }
        .flowOn(Dispatchers.IO)

    override fun hasBeers() = beerDataStore.hasBeers()
        .flowOn(Dispatchers.IO)

    override fun getBeersByPageForPaging(page: Int) : List<Beer>{

    }
}
