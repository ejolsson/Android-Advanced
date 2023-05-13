package com.example.androidfundamentals.data

// Hero model copied from HeroDTO, used for battle simulation

data class Hero(
    val name: String,
    val photo: String,
    val description: String,
    val maxLife: Int = 100,
    var currentLife: Int = 100
)