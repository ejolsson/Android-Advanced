package com.example.androidadvanced.data.remote

import android.util.Log
import com.example.androidadvanced.data.remote.response.GetLoginResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class LoginRemoteDataSource {

    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build() /// moshi: json library builder

    private val okHttpClient = // NOT SURE WHAT THIS DOES...
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dragonball.keepcoding.education/")// url
        .client(okHttpClient) // help build request
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())//.asLenient()
        .build()

    private val loginApi: LoginApi = retrofit.create(LoginApi::class.java)

    suspend fun getLogin2(username: String, password: String): String { // was suspend fun
        val credentials = okhttp3.Credentials.basic(username,password).toString()

        // Debug
        Log.d("Tag","okHttpClient: $okHttpClient") // prints: okHttpClient: okhttp3.OkHttpClient@ff8acbc
        Log.d("Tag", "retrofit: $retrofit") // prints: retrofit: retrofit2.Retrofit@6eb0445
        Log.d("Tag", "loginApi: $loginApi") // prints: loginApi: retrofit2.Retrofit$1@fbc079a
        Log.d("Tag", "credentials: $credentials") // prints: credentials: Basic ZWpvbHNzb25AZ21haWwuY29tOnZhbW9zUmFmYTIwMjMh // ✅same as v1
        Log.d("Tag", "loginApi.getLogin1(credentials): ${loginApi.getLogin1(credentials)}")

        return loginApi.getLogin1(credentials) // ⚠️ Unable to create converter for method LoginApi.getLogin1
        // PID: 32055 java.lang.IllegalArgumentException:
        // Unable to create converter for class com.example.androidadvanced.data.remote.response.GetLoginResponse
        // for method LoginApi.getLogin1
    }
}