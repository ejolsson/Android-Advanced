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
        .build()

    private val okHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dragonball.keepcoding.education/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .build()

    private val loginApi: LoginApi = retrofit.create(LoginApi::class.java)

    suspend fun getLogin2(username: String, password: String): String {
        val credentials = okhttp3.Credentials.basic(username,password)
//        Log.d("Tag", "loginApi.getLogin1(credentials): ${loginApi.getLogin1(credentials)}")
        return loginApi.getLogin1(credentials) // good print
    }
}