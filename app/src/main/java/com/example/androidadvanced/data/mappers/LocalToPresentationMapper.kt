package com.example.androidadvanced.data.mappers

import com.example.androidadvanced.data.local.model.LocalSuperHero
import com.example.androidadvanced.ui.model.SuperHero
import javax.inject.Inject

class LocalToPresentationMapper @Inject constructor() {

    fun mapLocalSuperHeroes(localSuperHeroes: List<LocalSuperHero>): List<SuperHero> {
        return localSuperHeroes.map { mapLocalSuperHero(it) }
    }

    private fun mapLocalSuperHero(getHeroesResponse: LocalSuperHero): SuperHero { // wasn't private before
        return SuperHero(
            getHeroesResponse.id,
            getHeroesResponse.name,
            getHeroesResponse.description,
            getHeroesResponse.photo,
            getHeroesResponse.favorite
        )
    }
}