package com.example.androidadvanced.data.mappers

import com.example.androidadvanced.data.local.model.LocalSuperHero
import com.example.androidadvanced.ui.model.SuperHero
import javax.inject.Inject

class PresentationToLocalMapper @Inject constructor() {

    fun mapPresentationSuperHeroFavorite(superHero: List<SuperHero>): List<LocalSuperHero> {
        return superHero.map { mapSuperHeroFavorite(it) }
    }

    fun mapSuperHeroFavorite(superHero: SuperHero): LocalSuperHero {
        return LocalSuperHero(
            superHero.id,
            superHero.name,
            superHero.description,
            superHero.photo,
            superHero.favorite
        )
    }
}