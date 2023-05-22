package com.example.androidadvanced.data.remote

import android.net.Credentials
import com.example.androidadvanced.data.remote.response.GetLoginResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

const val EMAILRAPID = "ejolsson@gmail.com" // todo:Remove. Testingonly.
const val PASSWORDRAPID = "vamosRafa2023!" // todo:Remove. Testingonly.
const val AUTHORIZATION = "Basic ZWpvbHNzb25AZ21haWwuY29tOnZhbW9zUmFmYTIwMjMh"

interface LoginApi {
    val email: String
    val password: String
//    val credentials = Credentials.basic(email,password)

    @POST("api/auth/login")
//    @Headers("Authorization", Credentials.basic(email, password)) // todo: pass same as v1
//    @Headers("Authorization: $AUTHORIZATION")
    suspend fun getLogin1(@Body username: String, password: String): GetLoginResponse // no body required
}