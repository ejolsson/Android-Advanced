package com.example.androidadvanced.data.remote

import com.example.androidadvanced.data.remote.response.GetHeroesResponse

// Principal mechanism to make API call

interface RemoteDataSource {
    suspend fun getHeroes2(): List<GetHeroesResponse>
//    suspend fun getHeroes2(): List<GetHeroesResponse> { // called in Repository.kt
//        return api.getHeroes1(GetHeroesRequestBody())
//    }
}
//class RemoteDataSource {
//
//    private val moshi = Moshi.Builder() // json parser
//        .addLast(KotlinJsonAdapterFactory())
//        .build() /// moshi: json library builder
//
//    private val okHttpClient =
//        OkHttpClient.Builder()
//            .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        }).build()
//
//    private val retrofit = Retrofit.Builder() // L2, 1.12.22~
//        .baseUrl("https://dragonball.keepcoding.education/")
//        .client(okHttpClient)
//        .addConverterFactory(MoshiConverterFactory.create(moshi))
//        .build()
//    private val api: DragonBallApi = retrofit.create(DragonBallApi::class.java)
//
//    suspend fun getHeroes2(): List<GetHeroesResponse> { // called in Repository.kt
//        return api.getHeroes1(GetHeroesRequestBody())
//    } // move up to interface above
//}
