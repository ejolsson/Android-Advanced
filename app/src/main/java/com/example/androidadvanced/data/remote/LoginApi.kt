package com.example.androidadvanced.data.remote

import android.net.Credentials
import com.example.androidadvanced.data.remote.request.GetLoginRequestBody
import com.example.androidadvanced.data.remote.response.GetLoginResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

const val EMAILRAPID = "ejolsson@gmail.com" // todo:Remove. Testingonly.
const val PASSWORDRAPID = "vamosRafa2023!" // todo:Remove. Testingonly.
const val AUTHORIZATION = "Basic ZWpvbHNzb25AZ21haWwuY29tOnZhbW9zUmFmYTIwMjMh"

interface LoginApi {
    @POST("api/auth/login")
    suspend fun getLogin1(@Header ("Authorization") credentials: String): String // no body required, can pass header
    // runtime parameter @path another option

/*
20230522
Try 1: run as is
13:19 error: Unable to create @Body converter for class

Try 2: 13:25 try removing @Body
13:@5 error: No Retrofit annotation found. (parameter #1) for method LoginApi.getLogin1

Try 3:
*/

}