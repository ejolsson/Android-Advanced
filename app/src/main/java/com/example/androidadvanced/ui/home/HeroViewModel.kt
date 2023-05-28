package com.example.androidadvanced.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidadvanced.data.Repository
import com.example.androidadvanced.ui.model.SuperHero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _heroListState = MutableStateFlow<HeroListState>(HeroListState.Idle)
    val heroListState: StateFlow<HeroListState> = _heroListState

    private val _heroState = MutableStateFlow<HeroState>(HeroState.Idle)
    val heroState: StateFlow<HeroState> = _heroState

    private val _detailState = MutableStateFlow<DetailState>(DetailState.Idle)
    val detailState: StateFlow<DetailState> = _detailState

    var selectedHero: SuperHero? = null

    private val _heroes = MutableLiveData<List<SuperHero>>()

    fun getHeroes5(token: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                Log.d("Tag", "getHeroes5 token = $token")
                repository.getHeroes4(token)
            }
            _heroes.value = result
            Log.d("Tag", "HeroVM > fun getHeroes5: List<SuperHero>.first = ${result.first()}")
            _heroListState.value = HeroListState.OnHeroListReceived(result)
        }
    }

    fun deleteHeroes5() {
        viewModelScope.launch {
            Log.d("Tag", "Heroes before delete: $_heroes")
            val result = withContext(Dispatchers.IO) {
                repository.deleteHeroes4()
                Log.d("Tag", "Heroes after delete: $_heroes")
            }
        }
    }

    fun selectHero(hero: SuperHero) {
        _heroListState.value = HeroListState.OnHeroSelected(hero)
        _heroState.value = HeroState.OnHeroReceived(hero)
        selectedHero = hero
    }

    fun makeHeroFavorite(hero: SuperHero) {
        Log.d("Tag DetailsFrag", "${hero.name} favorite status before: ${hero.favorite}")
        hero.favorite = true
    }

    fun goToMapPage(hero: SuperHero) {
        Log.d("Tag", "goToMapPage ${hero.name}")
        _detailState.value = DetailState.OnMapSelected(hero)
    }

    sealed class HeroListState {
        data class OnHeroListReceived(val heroes2: List<SuperHero>): HeroListState()
        data class OnHeroSelected(val hero: SuperHero): HeroListState()
        object OnHeroesUpdated: HeroListState()
        data class ErrorJSON(val error: String): HeroListState()
        data class ErrorResponse(val error: String): HeroListState()
        object Idle: HeroListState()
    }
    sealed class DetailState {
        data class OnMapSelected(val hero: SuperHero): DetailState()
        object Idle: DetailState()
    }
    sealed class HeroState {
        object Idle: HeroState()
        data class OnHeroReceived(val hero: SuperHero): HeroState()
    }
}