package com.example.beerapp.di

import android.content.Context
import androidx.room.Room
import com.example.beerapp.db.BeerDao
import com.example.beerapp.db.BeersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): BeersDatabase {
        return Room
            .databaseBuilder(context, BeersDatabase::class.java, "beer.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBeerDao(db: BeersDatabase): BeerDao {
        return db.beerDao()
    }
}
