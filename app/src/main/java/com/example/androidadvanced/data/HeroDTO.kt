package com.example.androidadvanced.data

// DataModel. This DTO receives hero data from Dragon Ballz API
// Todo: This gets replaced by GetHeroesResponse
data class HeroDTO(
    val id: String,
    val name: String,
    val photo: String,
    val description: String
    )