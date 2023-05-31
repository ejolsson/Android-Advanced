package com.example.androidadvanced.data.data

import android.util.Log
import com.example.androidadvanced.data.data.request.GetHeroesRequestBody
import com.example.androidadvanced.data.data.response.GetHeroesResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: DragonBallApi): RemoteDataSource {

    override suspend fun getHeroes2(token: String): List<GetHeroesResponse> {
        Log.d("Tag", "getHeroes2 token = $token")
        val tokenBear = "Bearer $token"
        return api.getHeroes1(tokenBear, GetHeroesRequestBody())
    }

    suspend fun getHeroes3(token: String): List<GetHeroesResponse> { // used for testing // was 2t
        val result = api.getHeroes1(token, GetHeroesRequestBody())
        return result.filter { it.name.startsWith("B") }
    }
}