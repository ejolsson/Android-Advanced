package com.example.androidadvanced.data.mappers

import com.example.androidadvanced.data.local.model.LocalSuperHero
import com.example.androidadvanced.data.local.model.LocalSuperHeroLocations
import com.example.androidadvanced.ui.model.SuperHero
import com.example.androidadvanced.ui.model.SuperHeroLocations
import javax.inject.Inject

class LocalToPresentationMapperLocations @Inject constructor() {

    fun mapLocalSuperHeroLocations(localSuperHeroLocations: List<LocalSuperHeroLocations>): List<SuperHeroLocations> {
        return localSuperHeroLocations.map { mapLocalSuperHeroLocation(it) }
    }

    private fun mapLocalSuperHeroLocation(getLocationsResponse: LocalSuperHeroLocations): SuperHeroLocations {
        return SuperHeroLocations (
            getLocationsResponse.id,
            getLocationsResponse.latitude,
            getLocationsResponse.longitude
        )
    }
}