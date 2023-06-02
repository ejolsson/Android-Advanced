package com.example.androidadvanced.data

import com.example.androidadvanced.data.local.model.LocalSuperHero
import com.example.androidadvanced.ui.model.SuperHero

interface Repository {
    suspend fun getHeroes4(token: String): List<SuperHero>
    suspend fun saveFavorite(hero: SuperHero): LocalSuperHero
    suspend fun deleteHeroes4()

}