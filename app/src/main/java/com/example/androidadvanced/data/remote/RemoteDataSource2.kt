package com.keepcoding.androidavanzado.data.remote

import com.example.androidadvanced.data.remote.request.GetHeroesRequestBody
import com.example.androidadvanced.data.remote.response.GetHeroesResponse

class RemoteDataSource2(private val api: DragonBallApi) {

    suspend fun getHeroes(): List<GetHeroesResponse> {
        val result = api.getHeros(GetHeroesRequestBody())

        val newList = result.filter { it.name.startsWith("B") }

        return newList
    }
}
