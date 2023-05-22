package com.example.androidadvanced.data.remote

import com.example.androidadvanced.data.remote.response.GetLoginResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class LoginRemoteDataSource {

//    private val moshi = Moshi.Builder() // json parser // NOT SURE I NEED THIS...
//        .addLast(KotlinJsonAdapterFactory())
//        .build() /// moshi: json library builder

    private val okHttpClient = // NOT SURE WHAT THIS DOES...
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dragonball.keepcoding.education/")
        .client(okHttpClient)
//        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val loginApi: LoginApi = retrofit.create(LoginApi::class.java)

    suspend fun getLogin2(username: String, password: String): GetLoginResponse { // was suspend fun
        return loginApi.getLogin1(username, password)
    }
} // gtg?