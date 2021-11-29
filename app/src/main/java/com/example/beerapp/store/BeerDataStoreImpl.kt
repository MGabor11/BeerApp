package com.example.beerapp.store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.beerapp.db.BeerDao
import com.example.beerapp.db.BeerDataModel
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.map

@Singleton
class BeerDataStoreImpl @Inject constructor(
    private val beerDao: BeerDao,
    private val userPreferencesDataStore: DataStore<Preferences>
) : BeerDataStore {

    private val SELECTED_BEER_ID = stringPreferencesKey("selected_beer_id")

    override fun saveBeers(list: List<BeerDataModel>) {
        beerDao.insert(list)
    }

    override suspend fun setSelectedBeerId(beerId: String) {
        userPreferencesDataStore.edit { preferences ->
            preferences[SELECTED_BEER_ID] = beerId
        }
    }

    override fun getSelectedBeerId() = userPreferencesDataStore.data
        .map { preferences ->
            preferences[SELECTED_BEER_ID] ?: ""
        }

    override fun getBeer(beerId: String) = beerDao.getBeer(beerId)

    override fun getBeers() = beerDao.getBeers()

    override fun hasBeers() = beerDao.getCount()
        .map { it > 0 }
}
