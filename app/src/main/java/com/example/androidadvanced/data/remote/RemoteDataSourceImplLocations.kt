package com.example.androidadvanced.data.remote

import android.util.Log
import com.example.androidadvanced.data.remote.request.GetLocationsRequestBody
import com.example.androidadvanced.data.remote.response.GetLocationsResponse
import javax.inject.Inject

class RemoteDataSourceImplLocations @Inject constructor(private val api: MapApi):
    RemoteDataSourceLocations {

    override suspend fun getLocations2(token: String): List<GetLocationsResponse> {
        Log.d("Tag", "getLocations2 token = $token")
        val tokenBear = "Bearer $token"
        return api.getLocations1(tokenBear, GetLocationsRequestBody())
    }
}