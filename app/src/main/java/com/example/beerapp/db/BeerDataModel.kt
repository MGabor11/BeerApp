package com.example.beerapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beer")
data class BeerDataModel(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val imageUrl: String
)
