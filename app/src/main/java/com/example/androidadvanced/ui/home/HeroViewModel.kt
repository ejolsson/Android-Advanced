package com.example.androidadvanced.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidadvanced.data.Repository
import com.example.androidadvanced.ui.model.SuperHero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeroViewModel(context: Context): ViewModel() {

    private val _heroListState = MutableStateFlow<HeroListState>(HeroListState.Idle)
    val heroListState: StateFlow<HeroListState> = _heroListState
    private val _heroState = MutableStateFlow<HeroState>(HeroState.Idle)
    val heroState: StateFlow<HeroState> = _heroState
    lateinit var heroesLiving: List<SuperHero> // was <Hero>, then <GetHeroesResponse>
    var selectedHero: SuperHero? = null // was Hero, then GetHeroesResponse

    private val repository = Repository(context)
    private val _heroes = MutableLiveData<List<SuperHero>>() // was val heroes, then GetHeroesResponse
    val heroes2: LiveData<List<SuperHero>> get() = _heroes // was val heroes, then GetHeroesResponse

    // advanced call
    fun getHeroes5() { // todo: add token parameter
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getHeroes4()
            }
            _heroes.value = result
//            Log.d("Tag getHeroes", _heroes.toString())
            Log.d("Tag", "HeroVM > fun getHeroes5: List<SuperHero>.first = ${result.first()}") // prints right result
            _heroListState.value = HeroListState.OnHeroListReceived(result)
        }
    }

    fun selectHero(hero: SuperHero) { // was Hero, then GetHeroesResponse
        _heroListState.value = HeroListState.OnHeroSelected(hero)
        _heroState.value = HeroState.OnHeroReceived(hero)
        selectedHero = hero
    }

    fun returnToHeroList() {
        Log.d("Tag HeroVM", "fun returnToHeroList()...")
        selectedHero?.let { hero ->
            _heroState.value = HeroState.HeroLifeZero(hero)
            heroesLiving.firstOrNull {it.name == hero.name}?.let {
                // TODO: create a transition back to HeroesList
                Log.d("Tag HeroVM", "Hi, need to create a transition back to HeroesList")
            }
        }
    }

    fun makeHeroFavorite(hero: SuperHero) {
        Log.d("Tag HeroVM", "fun makeHeroFavorite()...")
        Log.d("Tag DetailsFrag", "${hero.name} favorite status before: ${hero.favorite}")
        hero.favorite = true
    }

    sealed class HeroListState {
        object Idle: HeroListState()
//        data class OnHeroListReceived(val heroes: List<Hero>): HeroListState()
        data class OnHeroListReceived(val heroes2: List<SuperHero>): HeroListState() // was <Hero>, then GetHeroesResponse
        data class OnHeroSelected(val hero: SuperHero): HeroListState() // was Hero, then GetHeroesResponse

        object OnHeroesUpdated: HeroListState()
//        data class OnHeroDeath(val heroes: List<SuperHero>): HeroListState() // was <Hero> todo REMOVE
        data class ErrorJSON(val error: String): HeroListState()
        data class ErrorResponse(val error: String): HeroListState()
    }
    sealed class HeroState {
        object Idle: HeroState()
        data class OnHeroReceived(val hero: SuperHero): HeroState() // was Hero, then GetHeroesResponse
        data class HeroLifeZero(val hero: SuperHero): HeroState() // was Hero, then GetHeroesResponse
    }
}