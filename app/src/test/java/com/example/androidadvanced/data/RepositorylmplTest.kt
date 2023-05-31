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

class RepositorylmplTest {

    private lateinit var repositoryImpl: RepositoryImpl
    private lateinit var localDataSource: FakeLocalDataSource
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var remoteToLocalMapper: RemoteToLocalMapper
    private lateinit var localToPresentationMapper: LocalToPresentationMapper
    private lateinit var localDataSourceImpl: LocalDataSourceImpl
    // una mas cosa.... mapper... favorites presentation to local
    val testToken = "eyJhbGciOiJIUzI1NiIsImtpZCI6InByaXZhdGUiLCJ0eXAiOiJKV1QifQ.eyJpZGVudGlmeSI6IjdDNzQ1NjRCLTQ5NUEtNDhCRC04QzIyLTM5OEUwOUREODY0MyIsImV4cGlyYXRpb24iOjY0MDkyMjExMjAwLCJlbWFpbCI6Imp1YW5qZS5jaWxsYTFAZ21haWwuY29tIn0.epMHxtAkVu_fT5FvQwKrm_fRqzT9UOG2gpiTTipQajw"

    @Before
    fun setup() {
        localDataSource = FakeLocalDataSource()
        remoteDataSource = mockk()

        remoteToLocalMapper = RemoteToLocalMapper()
        localToPresentationMapper = LocalToPresentationMapper()

        repositoryImpl =
            RepositoryImpl(localDataSource, remoteDataSource, localToPresentationMapper, remoteToLocalMapper)
    }

    @Test // mock test
    fun `(FAKE) WHEN getHeroes2 EXPECT local empty return network`() = runTest {
        // GIVEN this simulation
        coEvery { remoteDataSource.getHeroes2(testToken) } returns generateGetHeroesResponse(16) // purpose:
        // coEvery { dependency.function() } returns generateThing..
        // mock or fake parts...

        // WHEN the function we want to test
        val actual = repositoryImpl.getHeroes4(testToken)
        Log.d("Test","actual: $actual")
        /*
        Prints: actual: [
        SuperHero(id=Name 0, name=ID 0, description=Description 0, photo=Photo 0, favorite=false),
        SuperHero(id=Name 1, name=ID 1, description=Description 1, photo=Photo 1, favorite=false),
        ...
        SuperHero(id=Name 15, name=ID 15, description=Description 15, photo=Photo 15, favorite=false)
        ]
         */

        // THEN / EXPECT
        assert(actual.isNotEmpty())
    } // pass

    @Test // mock test
    suspend fun `WHEN getHeroes2 EXPECT successful network response first call and successful local response next call`() {
        // GIVEN
        coEvery { remoteDataSource.getHeroes2(testToken) } returns generateGetHeroesResponse(16)
        coEvery { localDataSource.getHeroes3() } returns generateLocalSuperhero(16)

        // Is it the initial local db state an issue
        // WHEN
        val actual = repositoryImpl.getHeroes4(testToken) // big fun to get List<SuperHero> fm API, returns List<SuperHero>
        Log.d("Test","actual: $actual")
        val actual2 =

        // THEN
        assert(actual.isNotEmpty())
//        assert(actual2.isNotEmpty())???
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