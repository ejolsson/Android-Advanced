package com.example.androidadvanced.data

import com.example.androidadvanced.data.remote.response.GetHeroesResponse
import com.example.androidadvanced.data.remote.RemoteDataSource

class Repository {

    private val remoteDataSource = RemoteDataSource()

    suspend fun getHeroes(): List<GetHeroesResponse> {
        return remoteDataSource.getHeroes()
    } // called in SharedVM
}
