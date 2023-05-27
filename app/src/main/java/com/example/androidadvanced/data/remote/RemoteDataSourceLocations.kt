package com.example.androidadvanced.data.remote

import com.example.androidadvanced.data.remote.response.GetLocationsResponse

interface RemoteDataSourceLocations {
    suspend fun getLocations2(token: String): List<GetLocationsResponse>
}