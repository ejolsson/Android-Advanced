package com.example.androidadvanced.data.remote.request

import com.google.gson.annotations.SerializedName
import okhttp3.Credentials

//class GetLoginRequestBody {
//    val name: String = ""
//    // todo: update
//}

data class GetLoginRequestBody(
    val credentials: String //= Credentials.basic(username,password)
)