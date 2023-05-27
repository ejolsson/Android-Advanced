package com.example.androidadvanced.data.local

import com.example.androidadvanced.data.local.model.LocalSuperHero
import com.example.androidadvanced.data.local.model.LocalSuperHeroLocations

// L3, 22:05:22

interface LocalDataSourceLocations {
    suspend fun getLocations3(): List<LocalSuperHeroLocations>
    suspend fun insertLocations(localSuperHeroLocations: List<LocalSuperHeroLocations>)
}