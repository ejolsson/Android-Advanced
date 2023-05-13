package com.example.androidfundamentals.data

// DataModel
// This DTO receives hero data from Dragon Ballz API

data class HeroDTO(
    val id: String,
    val name: String,
    val photo: String,
    val description: String
    )