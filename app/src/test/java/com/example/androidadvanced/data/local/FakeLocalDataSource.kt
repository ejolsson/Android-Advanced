package com.example.androidadvanced.data.local

import com.example.androidadvanced.data.local.LocalDataSource
import com.example.androidadvanced.data.local.model.LocalSuperHero
import javax.inject.Inject

class FakeLocalDataSource @Inject constructor() : LocalDataSource {

    private var firstTime = true

    private var heroes = mutableListOf<LocalSuperHero>()

    override suspend fun getHeroes3(): List<LocalSuperHero> {
        return if (firstTime){
            firstTime = false
            emptyList()
        }  else {
            heroes // L6 update
        }
    }

    override suspend fun insertHero(localSuperhero: LocalSuperHero) {
        TODO("Not yet implemented")
    }

    override suspend fun insertHeroes(localSuperheros: List<LocalSuperHero>) {
        heroes.addAll(localSuperheros)
    }

    override suspend fun deleteHeroes3() {
        TODO("Not yet implemented")
    }
}