package com.example.androidadvanced.data.data

import android.util.Log
import com.example.androidadvanced.data.data.request.GetLocationsRequestBody
import com.example.androidadvanced.data.data.response.GetLocationsResponse
import javax.inject.Inject

class RemoteDataSourceImplLocations @Inject constructor(private val api: MapApi):
    RemoteDataSourceLocations {

    override suspend fun getLocations2(token: String, id: String): List<GetLocationsResponse> {
        Log.d("Tag", "getLocations2 token = $token")
        val tokenBear = "Bearer $token"
        lateinit var bodyX: GetLocationsRequestBody
        bodyX.id = id
        return api.getLocations1(tokenBear, bodyX)
    }
}