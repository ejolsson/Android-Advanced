package com.example.androidadvanced.di

import android.content.Context
import androidx.room.Room
import com.example.androidadvanced.data.local.SuperHeroDAO
import com.example.androidadvanced.data.local.SuperHeroDAOLocations
import com.example.androidadvanced.data.local.SuperHeroDatabase
import com.example.androidadvanced.data.local.SuperHeroDatabaseLocations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModuleLocations {

    @Provides
    fun providesSuperheroDatabaseLocations(@ApplicationContext context: Context): SuperHeroDatabaseLocations {
        val db = Room.databaseBuilder(
            context,
            SuperHeroDatabaseLocations::class.java, "superhero-db"
        ).build()
        return db
    }

    @Provides
    fun providesDaoLocations(db: SuperHeroDatabaseLocations): SuperHeroDAOLocations {
        val dao = db.superHeroDaoLocations()
        return dao
    }

}
