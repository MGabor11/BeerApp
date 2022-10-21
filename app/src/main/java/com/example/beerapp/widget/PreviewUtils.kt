package com.example.beerapp.widget

import com.example.beerapp.model.Beer

fun makeDummyBeerList():List<Beer> {
    val list = mutableListOf<Beer>()
    repeat(3){ id->
        val beer = Beer(
            id = id.toString(),
            name = "Beer $id",
            description = "Description $id",
            imageUrl = "https://images.punkapi.com/v2/keg.png"
        )
        list.add(beer)
    }
    return list
}