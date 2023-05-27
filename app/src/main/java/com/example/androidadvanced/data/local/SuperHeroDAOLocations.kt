package com.example.androidadvanced.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidadvanced.data.local.model.LocalSuperHero
import com.example.androidadvanced.data.local.model.LocalSuperHeroLocations

@Dao
interface SuperHeroDAOLocations {

    @Query("SELECT * FROM superheroeslocations")
    suspend fun getAll(): List<LocalSuperHeroLocations>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllVarag(vararg users: LocalSuperHeroLocations)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllList(users: List<LocalSuperHeroLocations>)
}