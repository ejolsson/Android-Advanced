package com.example.androidadvanced.data.mappers

import com.example.androidadvanced.data.local.model.LocalSuperHero
import com.example.androidadvanced.ui.model.SuperHero
import javax.inject.Inject

class LocalToPresentationMapper @Inject constructor() {

    fun mapLocalSuperHeroes(localSuperHeroes: List<LocalSuperHero>): List<SuperHero> {
        return localSuperHeroes.map { mapLocalSuperHero(it) }
    }

    private fun mapLocalSuperHero(localSuperHero: LocalSuperHero): SuperHero {
        return SuperHero(
            localSuperHero.id,
            localSuperHero.name,
            localSuperHero.description,
            localSuperHero.photo,
            localSuperHero.favorite
        )
    }
}