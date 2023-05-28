package com.example.androidadvanced.data.remote.request

data class GetLoginRequestBody(
    val credentials: String //= Credentials.basic(username,password)
)