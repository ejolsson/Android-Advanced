package com.example.androidadvanced.data.local

import com.example.androidadvanced.data.local.model.LocalSuperHeroLocations

interface LocalDataSourceLocations {
    suspend fun getLocations3(): List<LocalSuperHeroLocations>
    suspend fun insertLocations(localSuperHeroLocations: List<LocalSuperHeroLocations>)
}