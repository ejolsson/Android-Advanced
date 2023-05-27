package com.example.androidadvanced.ui.model

data class SuperHero(
    val id: String,
    val name: String,
    val description: String,
    val photo: String,
    var favorite: Boolean,
//    val latitude: Double?, // may have to break this out
//    val longitude: Double?
)
