package com.example.androidadvanced.data

import android.util.Log
import com.example.androidadvanced.data.local.LocalDataSource
import com.example.androidadvanced.data.mappers.LocalToPresentationMapper
import com.example.androidadvanced.data.mappers.RemoteToLocalMapper
import com.example.androidadvanced.data.remote.RemoteDataSource
import com.example.androidadvanced.ui.model.SuperHero
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val localToPresentationMapper: LocalToPresentationMapper,
    private val remoteToLocalMapper: RemoteToLocalMapper
): Repository {

    override suspend fun getHeroes4(): List<SuperHero> {
        if (localDataSource.getHeroes3().isEmpty()) {
            Log.d("Tag", "localDataSource.getHeroes3().isEmpty() is TRUE")
            val remoteSuperHeroes = remoteDataSource.getHeroes2()

            localDataSource.insertHeroes(remoteToLocalMapper.mapGetHeroResponse(remoteSuperHeroes))
        }

        return localToPresentationMapper.mapLocalSuperHeroes(localDataSource.getHeroes3())
    }

    override suspend fun deleteHeroes4() {
        localDataSource.deleteHeroes3()
    }
}