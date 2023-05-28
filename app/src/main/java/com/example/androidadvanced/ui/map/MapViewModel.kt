package com.example.androidadvanced.ui.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidadvanced.data.RepositoryLocations
import com.example.androidadvanced.ui.model.SuperHeroLocations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val repositoryLocations: RepositoryLocations): ViewModel() {

    private val _mapState = MutableStateFlow<MapState>(MapState.Idle)
    val mapState: StateFlow<MapState> = _mapState

    private val _locations = MutableLiveData<List<SuperHeroLocations>>()

    fun getLocations5(token: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                Log.d("Tag", "getLocations5 token = $token")
                repositoryLocations.getLocations4(token)
            }
            _locations.value = result
            Log.d("Tag","HeroVM > fun getLocations5: List<SuperHeroLocations>.first = ${result.first()}"
            )

            Log.d("Tag", "HeroVM > fun getLocations5: List<SuperHeroLocations> = $result")
        }
    }

    sealed class MapState {
        data class OnHeroLocationReceived(val heroLocations: List<SuperHeroLocations>): MapState()
        object Idle: MapState()
    }
}