package com.example.androidadvanced.data.data

import com.example.androidadvanced.data.data.response.GetLocationsResponse

interface RemoteDataSourceLocations {
    suspend fun getLocations2(token: String, id:String): List<GetLocationsResponse>
}