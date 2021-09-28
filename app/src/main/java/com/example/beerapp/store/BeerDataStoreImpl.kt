package com.example.beerapp.store

import com.example.beerapp.db.BeerDao
import com.example.beerapp.db.BeerDataModel
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.map

@Singleton
class BeerDataStoreImpl @Inject constructor(private val beerDao: BeerDao) : BeerDataStore {
    override fun saveBeers(list: List<BeerDataModel>) {
        beerDao.insert(list)
    }

    override fun getBeer(beerId: String) = beerDao.getBeer(beerId)

    override fun getBeers() = beerDao.getBeers()

    override fun hasBeers() = beerDao.getCount()
        .map { it > 0 }
}
