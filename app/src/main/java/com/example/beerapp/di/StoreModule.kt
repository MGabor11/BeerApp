package com.example.beerapp.di

import com.example.beerapp.store.BeerDataStore
import com.example.beerapp.store.BeerDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StoreModule {

    @Singleton
    @Binds
    abstract fun bindBeerDataStore(impl: BeerDataStoreImpl): BeerDataStore
}
