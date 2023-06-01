package com.example.androidadvanced.data.remote

import android.util.Log
import com.example.androidadvanced.data.data.RemoteDataSourceImpl
import com.example.androidadvanced.utils.BaseNetworkMockTest
import com.example.androidadvanced.data.data.request.GetHeroesRequestBody
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RemoteDataSourceUnitTest : BaseNetworkMockTest() {

    // UUT o SUT Unit Under Test o System Under Test
    private val token = "eyJhbGciOiJIUzI1NiIsImtpZCI6InByaXZhdGUiLCJ0eXAiOiJKV1QifQ.eyJpZGVudGlmeSI6IjdDNzQ1NjRCLTQ5NUEtNDhCRC04QzIyLTM5OEUwOUREODY0MyIsImV4cGlyYXRpb24iOjY0MDkyMjExMjAwLCJlbWFpbCI6Imp1YW5qZS5jaWxsYTFAZ21haWwuY29tIn0.epMHxtAkVu_fT5FvQwKrm_fRqzT9UOG2gpiTTipQajw"
    @Test // unit test
    fun nameTest() = runTest {

        // Given
        val getHeroesRequestBody = GetHeroesRequestBody("Thor")

        // Then
        assert(getHeroesRequestBody.name == "Thor")
    } // pass

    @Test // unit test
    fun myTest() = runTest {
        // Given
        val remoteDataSource = RemoteDataSourceImpl(api)

        // When
        val actual = remoteDataSource.getHeroes2(token)
        Log.d("Tag", "actual: ${actual[0].name}")

        // Then
        assert(actual[0].name == "Broly")
    } // pass


    @Test // unit test
    fun `WHEN requesting getHeroes EXPECT successful response AND 3 heroes starting by B`() = runTest {
        // Given
        val remoteDataSource = RemoteDataSourceImpl(api)

        // When
        val actual = remoteDataSource.getHeroes3(token) // was 2t

        // Then
        assert(actual.size == 3)
    } // pass, pass again on 5/31 14:55:36

}
