package com.example.androidadvanced.data.data

import com.example.androidadvanced.data.data.request.GetHeroesRequestBody
import com.example.androidadvanced.data.data.request.GetLocationsRequestBody
import com.example.androidadvanced.data.data.response.GetHeroesResponse
import com.example.androidadvanced.data.data.response.GetLocationsResponse
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

interface MapApi {
    @POST("api/heros/locations")
    suspend fun getLocations1(
        @Header ("Authorization") token: String,
        @Body getLocationsRequestBody: GetLocationsRequestBody
    )
            : List<GetLocationsResponse>
}