package com.example.androidadvanced.data

import com.example.androidadvanced.data.remote.LoginRemoteDataSource
import com.example.androidadvanced.data.remote.response.GetLoginResponse

class LoginRepository {

    private val loginRemoteDataSource = LoginRemoteDataSource()

    suspend fun getLogin3(username: String, password: String): GetLoginResponse {
        return loginRemoteDataSource.getLogin2(username, password)
    } // called in LoginVM
}