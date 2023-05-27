package com.example.androidadvanced.data.local

import com.example.androidadvanced.data.local.model.LocalSuperHero
import com.example.androidadvanced.data.local.model.LocalSuperHeroLocations

// L3, 22:05:22

interface LocalDataSource {
    suspend fun getHeroes3(): List<LocalSuperHero>
    suspend fun insertHero(localSuperHero: LocalSuperHero)
    suspend fun insertHeroes(localSuperheroes: List<LocalSuperHero>)
    suspend fun deleteHeroes3()
//    suspend fun getLocations(): List<LocalSuperHeroLocations>
}

/*
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
 */
