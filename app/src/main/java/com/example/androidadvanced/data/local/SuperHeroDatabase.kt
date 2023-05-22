package com.example.androidadvanced.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidadvanced.data.local.model.LocalSuperHero

@Database(entities = [LocalSuperHero::class], version = 1)
abstract class SuperHeroDatabase: RoomDatabase() {
    abstract fun superHeroDao(): SuperHeroDAO
}