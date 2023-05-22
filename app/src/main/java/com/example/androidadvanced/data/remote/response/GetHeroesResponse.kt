package com.example.androidadvanced.data.remote.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

data class GetHeroesResponse( // data model, called everywhere
    val name: String,
    val id: String,
    val description: String,
    val photo: String
)

// TODO: Check to see if below GetHerosResponseOld is needed

@Entity
data class GetHerosResponseOld(
    @PrimaryKey @ColumnInfo(name = "id") @Json(name = "id") val id: String,
    @ColumnInfo(name = "name") @Json(name = "name") val name: String,
    @ColumnInfo(name = "description") @Json(name = "description") val description: String,
    @ColumnInfo(name = "photo") @Json(name = "photo") val photo: String,
//    @ColumnInfo(name = "favorite") val favorite: Boolean?,
)

// NETWORK
data class GetHeroesResponse2(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "photo") val photo: String,
)