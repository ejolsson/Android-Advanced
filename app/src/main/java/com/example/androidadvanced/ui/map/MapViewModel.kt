package com.example.androidadvanced.ui.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidadvanced.data.RepositoryLocations
import com.example.androidadvanced.data.data.response.GetLocationsResponse
import com.example.androidadvanced.ui.model.SuperHeroLocations
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val repositoryLocations: RepositoryLocations): ViewModel() {

    private val _mapState = MutableStateFlow<MapState>(MapState.Idle)
    val mapState: StateFlow<MapState> = _mapState

    private val _locations = MutableLiveData<List<SuperHeroLocations>>() // was GetLocationsResponse

    private var locationsLiving = listOf<SuperHeroLocations>() // new/local data type, to receive data mapping
    var locationToShow: SuperHeroLocations? = null // new/local data type, to receive data mapping

    fun getLocations5(token: String, id:String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                Log.d("Tag", "getLocations5 token = $token")
                repositoryLocations.getLocations4(token, id)
            }
            _locations.value = result
            Log.d("Tag","HeroVM > fun getLocations5: List<SuperHeroLocations>.first = ${result.first()}"
            )

            Log.d("Tag", "HeroVM > fun getLocations5: List<SuperHeroLocations> = $result")
        }
    }

    fun getLocationsX(token: String, id: String) : List<SuperHeroLocations> {
//    fun getLocationsX(token: String, id: String) {
//        Log.d("Tag", "getLocationsX...")
        viewModelScope.launch(Dispatchers.IO) {
            val client = OkHttpClient()
            val baseUrl = "https://dragonball.keepcoding.education/api/"
            val url = "${baseUrl}/heros/locations"
            val body = FormBody.Builder()
                .add("id", id) // hardcode
                .build()
//            Log.d("Tag","tokenPublic used for fetchHeroes = $token")
            val request = Request.Builder()
                .url(url)
                .addHeader("Authorization","Bearer $token")
                .post(body)
                .build()
//            Log.d("Tag", "request = $request")
            val call = client.newCall(request)
            val response = call.execute()
            if (response.isSuccessful) {
                response.body?.let { responseBody ->
                    val gson = Gson()
                    try {
                        val response = responseBody.string()
                        val getLocationsResponseArray = gson.fromJson(response, Array<GetLocationsResponse>::class.java)
                        Log.d("Tag", "getLocationsResponseArray.asList (pre-mapping) = ${getLocationsResponseArray.asList()}")
                        val locationsFight = getLocationsResponseArray.toList().map { SuperHeroLocations(it.id, it.latitud, it.longitud) }
                        Log.d("Tag", "locationsFight (post-mapping) = $locationsFight")
                        locationsLiving = locationsFight // initialize living heroes with api data
                        Log.d("Tag", "locationsLiving = $locationsLiving")
                        _mapState.value = MapState.OnHeroLocationReceived(locationsFight)
                    } catch (ex: Exception) {
                        _mapState.value= MapState.ErrorJSON("Something went wrong in the fetchHeroes response")
                    }
                } ?: run { _mapState.value = MapState.ErrorResponse("Something went wrong in the fetchHeroes request") }
            }
            else {
                Log.e("Tag","Something went wrong in the fetchHeroes response")
            }

        }
        return locationsLiving
    }

    sealed class MapState {
        data class OnHeroLocationReceived(val heroLocations: List<SuperHeroLocations>): MapState()
        data class ErrorJSON(val error: String): MapState()
        data class ErrorResponse(val error: String): MapState()
        object Idle: MapState()
    }
}