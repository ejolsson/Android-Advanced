package com.example.androidadvanced.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "superheroeslocations")
data class LocalSuperHeroLocations (
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double
)