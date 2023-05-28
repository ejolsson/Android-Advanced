package com.example.androidadvanced.data

import com.example.androidadvanced.data.remote.LoginRemoteDataSource

class LoginRepository {

    private val loginRemoteDataSource = LoginRemoteDataSource()

    suspend fun getLogin3(username: String, password: String): String {
        return loginRemoteDataSource.getLogin2(username, password)
    }
}