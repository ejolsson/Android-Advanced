package com.example.androidadvanced.data.remote

import com.example.androidadvanced.utils.BaseNetworkMockTest
import com.example.androidadvanced.data.remote.request.GetHeroesRequestBody
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RemoteDataSourceTest : BaseNetworkMockTest() {

    // UUT o SUT Unit Under Test o System Under Test

    @Test
    fun myTest() = runTest {
        // Given
        val remoteDataSource = RemoteDataSource2(api)

        // When
        val actual = remoteDataSource.getHeroes()

        // Then
        assert(actual[0].name == "Broly")
    }


    @Test
    fun `WHEN requesting getHeros EXPECT successful response AND 3 heros starting by B`() = runTest {
        // Given
        val remoteDataSource = RemoteDataSource2(api)

        // When
        val actual = remoteDataSource.getHeroes()

        // Then
        assert(actual.size == 3)
    }


    @Test
    fun myTest3() = runTest {
        // Given
        val getHerosRequestBody = GetHeroesRequestBody("Juan")

        // When

        // Then
        assert(getHerosRequestBody.name == "Juan")
    }
}
