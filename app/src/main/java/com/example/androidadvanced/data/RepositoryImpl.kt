package com.example.androidadvanced.data

import android.util.Log
import com.example.androidadvanced.data.local.LocalDataSource
import com.example.androidadvanced.data.mappers.LocalToPresentationMapper
import com.example.androidadvanced.data.mappers.RemoteToLocalMapper
import com.example.androidadvanced.data.data.RemoteDataSource
import com.example.androidadvanced.ui.model.SuperHero
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val localToPresentationMapper: LocalToPresentationMapper,
    private val remoteToLocalMapper: RemoteToLocalMapper
): Repository {

    override suspend fun getHeroes4(token: String): List<SuperHero> {
        if (localDataSource.getHeroes3().isEmpty()) {
            Log.w("Tag", "No heroes stored locally. Going the fetch them!")
            Log.d("Tag", "getHeroes4 token = $token")
            val remoteSuperHeroes = remoteDataSource.getHeroes2(token)

            localDataSource.insertHeroes(remoteToLocalMapper.mapGetHeroResponse(remoteSuperHeroes))
        }
        return localToPresentationMapper.mapLocalSuperHeroes(localDataSource.getHeroes3())
    }

    override suspend fun deleteHeroes4() {
        localDataSource.deleteHeroes3()
    }
}