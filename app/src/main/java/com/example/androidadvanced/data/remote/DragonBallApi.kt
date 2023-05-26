package com.example.androidadvanced.data.remote

import com.example.androidadvanced.data.remote.request.GetHeroesRequestBody
import com.example.androidadvanced.data.remote.response.GetHeroesResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface DragonBallApi {
    @POST("api/heros/all")
    suspend fun getHeroes1(
        @Header ("Authorization") token: String,
        @Body getHeroesRequestBody: GetHeroesRequestBody)
    : List<GetHeroesResponse>
}