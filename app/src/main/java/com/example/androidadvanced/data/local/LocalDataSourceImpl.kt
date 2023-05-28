package com.example.androidadvanced.data.local

import com.example.androidadvanced.data.local.model.LocalSuperHero
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: SuperHeroDAO): LocalDataSource {

    override suspend fun getHeroes3(): List<LocalSuperHero> {
        return dao.getAll()
    }

    override suspend fun insertHero(localSuperHero: LocalSuperHero) {
        dao.insertAllList(listOf(localSuperHero))
    }

    override suspend fun insertHeroes(localSuperheroes: List<LocalSuperHero>) {
        dao.insertAllList(localSuperheroes)
    }

    override suspend fun deleteHeroes3() {
        dao.deleteAll()
    }
}