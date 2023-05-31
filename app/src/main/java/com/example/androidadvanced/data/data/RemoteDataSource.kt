package com.example.androidadvanced.data.data

import com.example.androidadvanced.data.data.response.GetHeroesResponse

interface RemoteDataSource {
    suspend fun getHeroes2(token: String): List<GetHeroesResponse>
}

