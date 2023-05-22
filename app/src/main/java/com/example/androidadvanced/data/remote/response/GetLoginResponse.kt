package com.example.androidadvanced.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetLoginResponse (
//    val token: String
    @SerializedName("token") val token: String
)