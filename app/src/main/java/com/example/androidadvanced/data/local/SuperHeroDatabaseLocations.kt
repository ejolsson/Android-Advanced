package com.example.androidadvanced.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidadvanced.data.local.model.LocalSuperHeroLocations

@Database(entities = [LocalSuperHeroLocations::class], version = 1)
abstract class SuperHeroDatabaseLocations: RoomDatabase() {
    abstract fun superHeroDaoLocations(): SuperHeroDAOLocations
}