package com.example.androidadvanced.di

import android.content.Context
import androidx.room.Room
import com.example.androidadvanced.data.local.SuperHeroDAO
import com.example.androidadvanced.data.local.SuperHeroDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun providesSuperheroDatabase(@ApplicationContext context: Context): SuperHeroDatabase {
        val db = Room.databaseBuilder(
            context,
            SuperHeroDatabase::class.java, "superhero-db"
        ).build()
        return db
    }

    @Provides
    fun providesDao(db: SuperHeroDatabase): SuperHeroDAO {
        val dao = db.superHeroDao()
        return dao
    }

}
