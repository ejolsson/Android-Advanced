package com.example.androidadvanced.data.mappers

import com.example.androidadvanced.data.local.model.LocalSuperHero
import com.example.androidadvanced.data.remote.response.GetHeroesResponse
import javax.inject.Inject

class RemoteToLocalMapper @Inject constructor() {

    fun mapGetHeroResponse(getHeroesResponse: List<GetHeroesResponse>): List<LocalSuperHero> {
        return getHeroesResponse.map { mapGetHeroesResponse(it) }
    }

    fun mapGetHeroesResponse(getHeroesResponse: GetHeroesResponse): LocalSuperHero {
        return LocalSuperHero(
            getHeroesResponse.id,
            getHeroesResponse.name,
            getHeroesResponse.description,
            getHeroesResponse.photo,
            false // force out api values
        )
    }
}