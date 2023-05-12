package com.example.androidfundamentals.data

// Hero model copied from HeroDTO, used for battle simulation

data class Hero(
    val name: String,
    val photo: String,
    val maxLife: Int = 100,
    var currentLife: Int = 100
) //{
//    var life: Int = 100 // life points
//}

// sample data
val listOfHeroesSample = listOf(
    Hero("Goku", "https://cdn.alfabetajuega.com/alfabetajuega/2020/12/goku1.jpg?width=300", 100, 100),
    Hero("Vegata", "https://cdn.alfabetajuega.com/alfabetajuega/2020/12/vegetita.jpg?width=300", 100, 0),
    Hero("Bulma", "https://cdn.alfabetajuega.com/alfabetajuega/2021/01/Bulma-Dragon-Ball.jpg?width=300", 100, -10),
    Hero("Krilin", "https://cdn.alfabetajuega.com/alfabetajuega/2020/08/Krilin.jpg?width=300", 100, 20),
    Hero("Freezer", "https://cdn.alfabetajuega.com/alfabetajuega/2020/12/freezer-dragon-ball-bebe-abj.jpg?width=300", 100,0),
    Hero("Mr. Satán", "https://cdn.alfabetajuega.com/alfabetajuega/2020/06/dragon-ball-satan.jpg?width=300", 100, 0)
)