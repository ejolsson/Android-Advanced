package com.example.androidadvanced.utils

import com.example.androidadvanced.data.local.model.LocalSuperHero
import com.example.androidadvanced.data.remote.response.GetHeroesResponse
import com.example.androidadvanced.ui.model.SuperHero

// L5, 0.41.20, L5, 2.05.45
fun generateHeros(size: Int): List<SuperHero> {
    return (0 until size).map { SuperHero(
        "ID $it",
        "Name $it",
        "Description $it",
        "Photo $it",
        false
    ) }
}


fun generateGetHerosResponse(size: Int): List<GetHeroesResponse> {
    return (0 until size).map { GetHeroesResponse("ID $it", "Name $it", "Description $it", "Photo $it") }
}

fun generateLocalSuperhero(size: Int): List<LocalSuperHero> {
    return (0 until size).map { LocalSuperHero(
        "ID $it",
        "Name $it",
        "Description $it",
        "Photo $it",
        false
    ) }
}
