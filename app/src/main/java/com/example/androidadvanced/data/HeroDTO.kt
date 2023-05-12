package com.example.androidfundamentals.data

// DataModel
// This DTO receives hero data from Dragon Ballz API

data class HeroDTO(
    val id: String,
    val name: String,
    val photo: String
    )

// sample data
val listOfHeroesDTO = listOf(
    HeroDTO("123", "Goku", "https://cdn.alfabetajuega.com/alfabetajuega/2020/12/goku1.jpg?width=300"),
    HeroDTO("456", "Vegata", "https://cdn.alfabetajuega.com/alfabetajuega/2020/12/vegetita.jpg?width=300"),
    HeroDTO("789", "Bulma", "https://cdn.alfabetajuega.com/alfabetajuega/2021/01/Bulma-Dragon-Ball.jpg?width=300")
)

//data class Hero(val name: String, val description: String, val photo: String) {
//    var life: Int = 100
//}