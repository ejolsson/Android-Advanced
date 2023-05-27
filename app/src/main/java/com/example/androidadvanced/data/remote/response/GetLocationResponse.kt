package com.example.androidadvanced.data.remote.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

data class GetLocationsResponse(
    val id: String,
    val latitud: Double,
    val longitud: Double
)


// NETWORK
data class GetLocationssResponse(
    @Json(name = "id") val id: String,
    @Json(name = "latitud") val latitud: Double,
    @Json(name = "longitud") val longitud: Double
)