package com.example.androidadvanced.data.remote

import com.example.androidadvanced.data.remote.request.GetHeroesRequestBody
import com.example.androidadvanced.data.remote.response.GetHeroesResponse
import javax.inject.Inject

// L6 intro
class RemoteDataSourceImpl @Inject constructor(private val api: DragonBallApi): RemoteDataSource {

    override suspend fun getHeroes2(): List<GetHeroesResponse> {
        return api.getHeroes1(GetHeroesRequestBody())
    }
}