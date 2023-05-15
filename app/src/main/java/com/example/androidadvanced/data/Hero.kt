package com.example.androidadvanced.data

// Hero model copied from HeroDTO, used for battle simulation

data class Hero(
    val id: String,
    val name: String,
    val photo: String,
    val description: String,
    var favorite: Boolean = false // initialize to false
)