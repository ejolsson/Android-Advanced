package com.example.androidadvanced.data.remote.request

data class GetHeroesRequestBody(
    val name: String = ""
) // called in RemoteDataSource.kt, RemoteDataSource2.kt, RemoteDataSourceTest.kt
