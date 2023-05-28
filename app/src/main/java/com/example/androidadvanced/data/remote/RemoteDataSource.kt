package com.example.androidadvanced.data.remote

import com.example.androidadvanced.data.remote.response.GetHeroesResponse

interface RemoteDataSource {
    suspend fun getHeroes2(token: String): List<GetHeroesResponse>
}

