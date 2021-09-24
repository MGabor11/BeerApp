package com.example.beerapp.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Database(entities = [BeerDataModel::class], version = 1)
abstract class BeersDatabase : RoomDatabase() {

    abstract fun beerDao(): BeerDao
}

@Dao
interface BeerDao {

    @Query("SELECT * FROM beer")
    fun getBeers(): Flow<List<BeerDataModel>>

    @Query("SELECT * FROM beer WHERE id = :beerId")
    fun getBeer(beerId: String): Flow<BeerDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: List<BeerDataModel>)
}
