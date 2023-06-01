package com.example.androidadvanced.data

import android.util.Log
import com.example.androidadvanced.data.data.RemoteDataSource
import com.example.androidadvanced.data.local.FakeLocalDataSource
import com.example.androidadvanced.data.local.LocalDataSourceImpl
import com.example.androidadvanced.data.mappers.LocalToPresentationMapper
import com.example.androidadvanced.data.mappers.RemoteToLocalMapper
import com.example.androidadvanced.utils.generateGetHeroesResponse
import com.example.androidadvanced.utils.generateLocalSuperhero
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RepositorylmplMockTest {

    private lateinit var repositoryImpl: RepositoryImpl
    private lateinit var localDataSource: FakeLocalDataSource
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var remoteToLocalMapper: RemoteToLocalMapper
    private lateinit var localToPresentationMapper: LocalToPresentationMapper
    private lateinit var localDataSourceImpl: LocalDataSourceImpl
    // una mas cosa.... mapper... favorites presentation to local
    private val testToken = "eyJhbGciOiJIUzI1NiIsImtpZCI6InByaXZhdGUiLCJ0eXAiOiJKV1QifQ.eyJpZGVudGlmeSI6IjdDNzQ1NjRCLTQ5NUEtNDhCRC04QzIyLTM5OEUwOUREODY0MyIsImV4cGlyYXRpb24iOjY0MDkyMjExMjAwLCJlbWFpbCI6Imp1YW5qZS5jaWxsYTFAZ21haWwuY29tIn0.epMHxtAkVu_fT5FvQwKrm_fRqzT9UOG2gpiTTipQajw"

    @Before
    fun setup() {
        localDataSource = mockk() // FakeLocalDataSource()
        remoteDataSource = mockk()

        remoteToLocalMapper = RemoteToLocalMapper()
        localToPresentationMapper = LocalToPresentationMapper()

        repositoryImpl =
            RepositoryImpl(localDataSource, remoteDataSource, localToPresentationMapper, remoteToLocalMapper)
    }

    @Test // mock test
    fun `WHEN getHeroes2 EXPECT successful network response first call and successful local response next call`() = runTest {
        // GIVEN
        coEvery { remoteDataSource.getHeroes2(testToken) } returns generateGetHeroesResponse(16)
        coEvery { localDataSource.getHeroes3() } returns generateLocalSuperhero(16)

        // Is it the initial local db state an issue
        // WHEN
        val actual = repositoryImpl.getHeroes4(testToken) // big fun to get List<SuperHero> fm API, returns List<SuperHero>
        Log.d("Test","actual: $actual")
        val actual2 = localDataSource.getHeroes3()

        // THEN
        assert(actual.isNotEmpty())
        assert(actual2.isNotEmpty())
    }

    // TODO: put this test in another file called LocalDataSourceImpTest
//    @Test
//    suspend fun `WHEN getHeroes3 EXPECT LocalHeroes count equals 17 after insertHero` () {
//
//        // GIVEN
//        coEvery { localDataSource.getHeroes3() } returns generateLocalSuperhero(16)
//        coEvery { localDataSource.insertHero() }
//
//        // WHEN
//        val actual3 = localDataSource.getHeroes3() // foundation number of heroes
//        val newHero = localDataSourceImp  // addtiona
//
//        // THEN / EXPECT
//
//    }
}