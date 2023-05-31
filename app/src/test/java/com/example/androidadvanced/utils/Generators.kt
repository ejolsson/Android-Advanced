package com.example.androidadvanced.utils

import com.example.androidadvanced.data.local.model.LocalSuperHero
import com.example.androidadvanced.data.data.response.GetHeroesResponse
import com.example.androidadvanced.ui.model.SuperHero

fun generateHeroes(size: Int): List<SuperHero> {
    return (0 until size).map { SuperHero(
        "ID $it",
        "Name $it",
        "Description $it",
        "Photo $it",
        false
    ) }
}


fun generateGetHeroesResponse(size: Int): List<GetHeroesResponse> {
    return (0 until size).map { GetHeroesResponse("ID $it", "Name $it", "Description $it", "Photo $it") }
}

fun generateLocalSuperhero(size: Int): List<LocalSuperHero> {
    return (0 until size).map { LocalSuperHero(
        "Id $it",
        "Name $it",
        "Description $it",
        "Photo $it",
        false
    ) }
}

fun generateSuperhero(size: Int): List<SuperHero> {
    return (0 until size).map { SuperHero(
        "Id $it",
        "Name $it",
        "Description $it",
        "Photo $it",
        false
    ) }
}