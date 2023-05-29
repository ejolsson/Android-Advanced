package com.example.androidadvanced.data

import com.example.androidadvanced.ui.model.SuperHeroLocations

interface RepositoryLocations {
    suspend fun getLocations4(token: String, id:String): List<SuperHeroLocations>
}