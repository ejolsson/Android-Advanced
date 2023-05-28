package com.example.androidadvanced.data.remote

import com.example.androidadvanced.utils.BaseNetworkMockTest
import com.example.androidadvanced.data.remote.request.GetHeroesRequestBody
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RemoteDataSourceTest : BaseNetworkMockTest() {

    // UUT o SUT Unit Under Test o System Under Test
    val token = "eyJhbGciOiJIUzI1NiIsImtpZCI6InByaXZhdGUiLCJ0eXAiOiJKV1QifQ.eyJpZGVudGlmeSI6IjdDNzQ1NjRCLTQ5NUEtNDhCRC04QzIyLTM5OEUwOUREODY0MyIsImV4cGlyYXRpb24iOjY0MDkyMjExMjAwLCJlbWFpbCI6Imp1YW5qZS5jaWxsYTFAZ21haWwuY29tIn0.epMHxtAkVu_fT5FvQwKrm_fRqzT9UOG2gpiTTipQajw"
    @Test
    fun nameTest() = runTest {

        // Given
        val getHeroesRequestBody = GetHeroesRequestBody("Thor")

        // Then
        assert(getHeroesRequestBody.name == "Thor")
    } // pass

    @Test
    fun myTest() = runTest {
        // Given
        val remoteDataSource = RemoteDataSourceImpl(api)

        // When
        val actual = remoteDataSource.getHeroes2(token)

        // Then
        assert(actual[0].name == "Broly")
    } // pass


    @Test
    fun `WHEN requesting getHeroes EXPECT successful response AND 3 heroes starting by B`() = runTest {
        // Given
        val remoteDataSource = RemoteDataSourceImpl(api)

        // When
        val actual = remoteDataSource.getHeroes3(token)

        // Then
        assert(actual.size == 3)
    } // pass

}
