package com.example.androidadvanced.data.remote.request

import com.google.gson.annotations.SerializedName

//class GetLoginRequestBody {
//    val name: String = ""
//    // todo: update
//}

data class GetLoginRequestBody(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
)