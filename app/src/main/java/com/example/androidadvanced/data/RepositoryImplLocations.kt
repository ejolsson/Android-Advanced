package com.example.androidadvanced.data

import android.util.Log
import com.example.androidadvanced.data.local.LocalDataSourceLocations
import com.example.androidadvanced.data.mappers.LocalToPresentationMapperLocations
import com.example.androidadvanced.data.mappers.RemoteToLocalMapperLocations
import com.example.androidadvanced.data.remote.RemoteDataSourceLocations
import com.example.androidadvanced.ui.model.SuperHeroLocations
import javax.inject.Inject

class RepositoryImplLocations @Inject constructor(
    private val localDataSourceLocations: LocalDataSourceLocations,
    private val remoteDataSourceLocations: RemoteDataSourceLocations,
    private val localToPresentationMapperLocations: LocalToPresentationMapperLocations,
    private val remoteToLocalMapperLocations: RemoteToLocalMapperLocations
): RepositoryLocations {
    override suspend fun getLocations4(token: String, id: String): List<SuperHeroLocations> {
        if (localDataSourceLocations.getLocations3().isEmpty()) {
            Log.w("Tag", "No locations stored locally. Going the fetch them!")
            Log.d("Tag", "getLocations4 token = $token")
            val remoteSuperHeroesLocations = remoteDataSourceLocations.getLocations2(token, id)

            localDataSourceLocations.insertLocations(remoteToLocalMapperLocations.mapGetLocationsResponse(remoteSuperHeroesLocations))
        }
        return localToPresentationMapperLocations.mapLocalSuperHeroLocations(localDataSourceLocations.getLocations3())
    }

}