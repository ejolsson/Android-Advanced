package com.example.androidadvanced.data.data.request

data class GetLoginRequestBody(
    val credentials: String //= Credentials.basic(username,password)
)