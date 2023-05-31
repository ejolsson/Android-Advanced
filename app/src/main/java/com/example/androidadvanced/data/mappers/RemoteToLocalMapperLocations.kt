package com.example.androidadvanced.data.mappers

import com.example.androidadvanced.data.local.model.LocalSuperHeroLocations
import com.example.androidadvanced.data.data.response.GetLocationsResponse
import javax.inject.Inject

class RemoteToLocalMapperLocations @Inject constructor() {

    fun mapGetLocationsResponse(getLocationsResponse: List<GetLocationsResponse>): List<LocalSuperHeroLocations> {
        return getLocationsResponse.map { mapGetLocationsResponse(it) }
    }

    fun mapGetLocationsResponse(getLocationsResponse: GetLocationsResponse): LocalSuperHeroLocations {
        return LocalSuperHeroLocations(
            getLocationsResponse.id,
            getLocationsResponse.latitud,
            getLocationsResponse.longitud
        )
    }
}