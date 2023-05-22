package com.example.androidadvanced.data.remote

import com.example.androidadvanced.data.remote.request.GetHeroesRequestBody
import com.example.androidadvanced.data.remote.response.GetHeroesResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

const val TOKEN =
    "eyJhbGciOiJIUzI1NiIsImtpZCI6InByaXZhdGUiLCJ0eXAiOiJKV1QifQ.eyJpZGVudGlmeSI6IjdDNzQ1NjRCLTQ5NUEtNDhCRC04QzIyLTM5OEUwOUREODY0MyIsImV4cGlyYXRpb24iOjY0MDkyMjExMjAwLCJlbWFpbCI6Imp1YW5qZS5jaWxsYTFAZ21haWwuY29tIn0.epMHxtAkVu_fT5FvQwKrm_fRqzT9UOG2gpiTTipQajw"

interface DragonBallApi { // helps formulate API request
    // takes reqBody, returns List response, applies get/post & headers
    @POST("api/heros/all")
    @Headers("Authorization: Bearer $TOKEN") // todo: pull in login token
    suspend fun getHeroes1(@Body getHeroesRequestBody: GetHeroesRequestBody): List<GetHeroesResponse>
} // called in RemoteDataSource.kt, RemoteDataSource2.kt, BaseNetworkMockTest.kt

