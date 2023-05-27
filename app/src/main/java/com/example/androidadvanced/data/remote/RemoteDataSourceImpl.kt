package com.example.androidadvanced.data.remote

import android.util.Log
import com.example.androidadvanced.data.remote.request.GetHeroesRequestBody
import com.example.androidadvanced.data.remote.response.GetHeroesResponse
import com.example.androidadvanced.data.remote.response.GetLocationsResponse
import javax.inject.Inject

// L6 intro
class RemoteDataSourceImpl @Inject constructor(private val api: DragonBallApi): RemoteDataSource {

    override suspend fun getHeroes2(token: String): List<GetHeroesResponse> {
        Log.d("Tag", "getHeroes2 token = $token")
        val tokenBear = "Bearer $token"
        return api.getHeroes1(tokenBear, GetHeroesRequestBody())
    }
}