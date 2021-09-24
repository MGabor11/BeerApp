package com.example.beerapp.di

import com.example.beerapp.network.BeerRemoteService
import com.example.beerapp.network.BeerRemoteServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteServiceModule {

    @Singleton
    @Binds
    abstract fun bindBeerRemoteService(impl: BeerRemoteServiceImpl): BeerRemoteService
}
