package com.example.androidadvanced.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidadvanced.data.local.model.LocalSuperHero
// L3, 21:39:34
@Dao
interface SuperHeroDAO {

    @Query("SELECT * FROM superheroes")
    suspend fun getAll(): List<LocalSuperHero>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllVarag(vararg users: LocalSuperHero)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllList(users: List<LocalSuperHero>)

    @Delete
    suspend fun delete(user: LocalSuperHero)

//    @Delete
//    fun clearAllTables(): Unit

    @Query("DELETE FROM superheroes")
    fun deleteAll()
}