package com.example.androidadvanced.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidadvanced.data.RepositoryLocations
import com.example.androidadvanced.data.Repository
import com.example.androidadvanced.ui.model.SuperHero
import com.example.androidadvanced.ui.model.SuperHeroLocations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(private val repository: Repository, private val repositoryLocations: RepositoryLocations): ViewModel() { // removed , context: Context fm injection

    private val _heroListState = MutableStateFlow<HeroListState>(HeroListState.Idle)
    val heroListState: StateFlow<HeroListState> = _heroListState

    private val _heroState = MutableStateFlow<HeroState>(HeroState.Idle)
    val heroState: StateFlow<HeroState> = _heroState

    private val _detailState = MutableStateFlow<DetailState>(DetailState.Idle)
    val detailState: StateFlow<DetailState> = _detailState

    private val _mapState = MutableStateFlow<MapState>(MapState.Idle)
    val mapState: StateFlow<MapState> = _mapState

    private lateinit var heroesLiving: List<SuperHero>
    var selectedHero: SuperHero? = null

    private val _heroes = MutableLiveData<List<SuperHero>>()
    val heroes2: LiveData<List<SuperHero>> get() = _heroes

    private val _locations = MutableLiveData<List<SuperHeroLocations>>()

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

    fun getLocations5(token: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                Log.d("Tag", "getLocations5 token = $token")
                repositoryLocations.getLocations4(token)
            }
            _locations.value = result

            Log.d("Tag", "HeroVM > fun getLocations5: List<SuperHeroLocations>.first = ${result.first()}")

            Log.d("Tag", "HeroVM > fun getLocations5: List<SuperHeroLocations> = $result")

//            _heroListState.value = HeroListState.OnHeroLocationReceived(result) // OnHeroLocationReceived went away..
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

//    fun returnToHeroList() {
//        Log.d("Tag HeroVM", "fun returnToHeroList()...")
//        selectedHero?.let { hero ->
//            _heroState.value = HeroState.HeroLifeZero(hero)
//            heroesLiving.firstOrNull {it.name == hero.name}?.let {
//                // TODO: create a transition back to HeroesList
//                Log.d("Tag HeroVM", "Hi, need to create a transition back to HeroesList")
//            }
//        }
//    }

    fun makeHeroFavorite(hero: SuperHero) {
        Log.d("Tag HeroVM", "fun makeHeroFavorite()...")
        Log.d("Tag DetailsFrag", "${hero.name} favorite status before: ${hero.favorite}")
        hero.favorite = true
    }

    fun goToMapPage(hero: SuperHero) { // #5
        Log.d("Tag", "#5 goToMapPage") // prints ok
        Log.d("Tag", "goToMapPage ${hero.name}")
        _detailState.value = DetailState.OnMapSelected(hero)
    }

    sealed class HeroListState {
        // 1
        data class OnHeroListReceived(val heroes2: List<SuperHero>): HeroListState() // was <Hero>, then GetHeroesResponse
//      data class OnHeroListReceived(val heroes: List<Hero>): HeroListState()
//      data class OnHeroLocationReceived(val heroLocation: List<SuperHeroLocations>): HeroListState()

        // 2
        data class OnHeroSelected(val hero: SuperHero): HeroListState() // was Hero, then GetHeroesResponse

        // 3
        object OnHeroesUpdated: HeroListState()

//        // 4 moved to detailState
//        data class OnMapSelected(val hero: SuperHero): HeroListState() // #4

        // 5
        data class ErrorJSON(val error: String): HeroListState()

        // 6
        data class ErrorResponse(val error: String): HeroListState()

        // 7
        object Idle: HeroListState()
    }
    sealed class DetailState {
        // 4
        data class OnMapSelected(val hero: SuperHero): DetailState() // #4
        object Idle: DetailState()
    }
    sealed class HeroState {
        object Idle: HeroState()
        data class OnHeroReceived(val hero: SuperHero): HeroState() // was Hero, then GetHeroesResponse
//        data class HeroLifeZero(val hero: SuperHero): HeroState() // was Hero, then GetHeroesResponse
//        object OnMapButtonPressed: HeroState()
    }
    sealed class MapState {
        data class OnHeroLocationReceived(val heroLocations: List<SuperHeroLocations>): MapState()
        object Idle: MapState()
    }
}