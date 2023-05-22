package com.example.androidadvanced.data.remote

import android.util.Log
import com.example.androidadvanced.data.remote.request.GetHeroesRequestBody
import com.example.androidadvanced.data.remote.response.GetHeroesResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
// Principal mechanism to make API call
class RemoteDataSource {

    private val moshi = Moshi.Builder() // json parser
        .addLast(KotlinJsonAdapterFactory())
        .build() /// moshi: json library builder

    private val okHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

    private val retrofit = Retrofit.Builder() // L2, 1.12.22~
        .baseUrl("https://dragonball.keepcoding.education/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
    private val api: DragonBallApi = retrofit.create(DragonBallApi::class.java)

    suspend fun getHeroes2(): List<GetHeroesResponse> { // called in Repository.kt
        Log.d("Tag RemoteDataSource", "moshi: $moshi")
        // prints: moshi: com.squareup.moshi.Moshi@ea783b
        Log.d("Tag RemoteDataSource", "okHttpClient: $okHttpClient")
        // prints: okHttpClient: okhttp3.OkHttpClient@d195b58
        Log.d("Tag RemoteDataSource", "retrofit: $retrofit")
        // prints: retrofit: retrofit2.Retrofit@bb0e4b1
        Log.d("Tag RemoteDataSource", "api: $api")
        // prints: api: retrofit2.Retrofit$1@2fe5d17
        return api.getHeroes1(GetHeroesRequestBody())
    }
}
