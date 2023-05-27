package com.example.androidadvanced.data.local

import com.example.androidadvanced.data.local.model.LocalSuperHero
import com.example.androidadvanced.data.local.model.LocalSuperHeroLocations
import javax.inject.Inject

class LocalDataSourceImplLocations @Inject constructor(private val dao: SuperHeroDAOLocations): LocalDataSourceLocations {

    override suspend fun getLocations3(): List<LocalSuperHeroLocations> {
        return dao.getAll()
    }

    suspend fun insertLocation(localSuperHeroLocations: LocalSuperHeroLocations) {
        dao.insertAllList(listOf(localSuperHeroLocations))
    }

    override suspend fun insertLocations(localSuperHeroLocations: List<LocalSuperHeroLocations>) {
        dao.insertAllList(localSuperHeroLocations)
    }
}