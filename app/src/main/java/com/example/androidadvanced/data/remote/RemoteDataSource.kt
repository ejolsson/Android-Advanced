package com.keepcoding.androidavanzado.data.remote

import com.example.androidadvanced.data.remote.request.GetHeroesRequestBody
import com.example.androidadvanced.data.remote.response.GetHeroesResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RemoteDataSource {

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
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
    private val api: DragonBallApi = retrofit.create(DragonBallApi::class.java)

    suspend fun getHeroes(): List<GetHeroesResponse> {
        return api.getHeroes(GetHeroesRequestBody())
    }
}
