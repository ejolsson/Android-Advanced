package com.example.androidadvanced.data.local

import com.example.androidadvanced.data.local.model.LocalSuperHero
import com.example.androidadvanced.data.local.model.LocalSuperHeroLocations

interface LocalDataSource {
    suspend fun getHeroes3(): List<LocalSuperHero>
    suspend fun insertHero(localSuperHero: LocalSuperHero)
    suspend fun insertHeroes(localSuperheroes: List<LocalSuperHero>)
    suspend fun deleteHeroes3()

}
