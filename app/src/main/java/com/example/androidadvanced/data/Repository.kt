package com.example.androidadvanced.data

import android.content.Context
import android.util.Log
import com.example.androidadvanced.data.local.LocalDataSource
import com.example.androidadvanced.data.mappers.LocalToPresentationMapper
import com.example.androidadvanced.data.mappers.RemoteToLocalMapper
import com.example.androidadvanced.data.remote.RemoteDataSource
import com.example.androidadvanced.ui.model.SuperHero

interface Repository {
    suspend fun getHeroes4(): List<SuperHero>
    suspend fun deleteHeroes4()

    /*
    class Repository(context: Context) {

    private val remoteDataSource = RemoteDataSource()
    private val localDataSource = LocalDataSource(context = context)
    private val remoteToLocalMapper = RemoteToLocalMapper()
    private val localToPresentationMapper = LocalToPresentationMapper()

    suspend fun getHeroes4(): List<SuperHero> {
        if (localDataSource.getHeroes3().isEmpty()) {
            Log.d("Tag", "localDataSource.getHeroes3().isEmpty() is TRUE")
            val remoteSuperHeroes = remoteDataSource.getHeroes2()
            // getHeroes2() returns api.getHeroes1(GetHeroesRequestBody())
            localDataSource.insertHeroes(remoteToLocalMapper.mapGetHeroResponse(remoteSuperHeroes))
        }

        return localToPresentationMapper.mapLocalSuperHeroes(localDataSource.getHeroes3())
    } // called in SharedVM

     */
}
