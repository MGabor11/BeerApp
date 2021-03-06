package com.example.beerapp.di

import com.example.beerapp.repository.BeerRepository
import com.example.beerapp.repository.BeerRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindBeerRepository(impl: BeerRepositoryImpl): BeerRepository
}
