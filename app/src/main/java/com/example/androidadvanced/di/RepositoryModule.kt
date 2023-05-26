package com.example.androidadvanced.di

import com.example.androidadvanced.data.Repository
import com.example.androidadvanced.data.RepositoryImpl
import com.example.androidadvanced.data.local.LocalDataSource
import com.example.androidadvanced.data.local.LocalDataSourceImpl
import com.example.androidadvanced.data.remote.RemoteDataSource
import com.example.androidadvanced.data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// L4 3.28.32, L5 no chg, L6
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    abstract fun bindsLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource // already an interface

    @Binds
    abstract fun bindsRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}