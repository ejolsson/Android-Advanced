package com.example.androidadvanced.data.remote

import retrofit2.http.Header
import retrofit2.http.POST

interface LoginApi {
    @POST("api/auth/login")
    suspend fun getLogin1(@Header ("Authorization") credentials: String): String
}