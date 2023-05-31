package com.example.androidadvanced.di

import com.example.androidadvanced.data.RepositoryLocations
import com.example.androidadvanced.data.RepositoryImplLocations
import com.example.androidadvanced.data.local.LocalDataSourceImplLocations
import com.example.androidadvanced.data.local.LocalDataSourceLocations
import com.example.androidadvanced.data.data.RemoteDataSourceImplLocations
import com.example.androidadvanced.data.data.RemoteDataSourceLocations
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModuleLocations {
    @Binds
    abstract fun bindsRepositoryLocations(repositoryImplLocations: RepositoryImplLocations): RepositoryLocations

    @Binds
    abstract fun bindsLocalDataSourceLocations(localDataSourceImplLocations: LocalDataSourceImplLocations): LocalDataSourceLocations

    @Binds
    abstract fun bindsRemoteDataSourceLocations(remoteDataSourceImplLocations: RemoteDataSourceImplLocations): RemoteDataSourceLocations
}