package com.example.androidadvanced.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
// L3 20:34:56
@Entity(tableName = "superheroes")
data class LocalSuperHero (
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "photo") val photo: String,
    @ColumnInfo(name = "favorite") val favorite: Boolean,
//    @ColumnInfo(name = "latitude") val latitude: Double,
//    @ColumnInfo(name = "longitude") val longitude: Double
)