package com.example.androidadvanced.data.local

import android.content.Context
import androidx.room.Room
import com.example.androidadvanced.data.local.model.LocalSuperHero

// L3, 22:05:22
class LocalDataSource (private val context: Context) {

    private  val db = Room.databaseBuilder(
        context,
        SuperHeroDatabase::class.java, "superhero-db" // check casing
    ).build()

    private val dao = db.superHeroDao()

    suspend fun getHeroes3(): List<LocalSuperHero>{
        return dao.getAll()
    }

    suspend fun insertHero(localSuperHero: LocalSuperHero){
        dao.insertAllList(listOf(localSuperHero))
    }

    suspend fun insertHeroes(localSuperHeroes: List<LocalSuperHero>){
        dao.insertAllList(localSuperHeroes)
    }
}