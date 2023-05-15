package com.example.androidadvanced.data.remote

import com.example.androidadvanced.data.remote.request.GetHeroesRequestBody
import com.example.androidadvanced.data.remote.response.GetHeroesResponse
// ? Just used for testing??
class RemoteDataSource2(private val api: DragonBallApi) {

    suspend fun getHeroes(): List<GetHeroesResponse> {
        val result = api.getHeroes(GetHeroesRequestBody()) // standard api call

        val newList = result.filter { it.name.startsWith("B") } // filter results

        return newList // return results
    }
}
