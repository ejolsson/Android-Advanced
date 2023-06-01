package com.example.androidadvanced.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
//import androidx.core.content.PermissionChecker.checkPermission
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.androidadvanced.R
import com.example.androidadvanced.data.User
import com.example.androidadvanced.databinding.MapBinding
import com.example.androidadvanced.ui.model.SuperHero
import com.example.androidadvanced.ui.model.SuperHeroLocations
import com.example.androidadvanced.utils.viewBinding
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch
//import java.security.AccessController.checkPermission // suspect causing an error with "checkPermission()"

class LocationsFragment (private var hero: SuperHero, private var heroLocations: List<SuperHeroLocations>): Fragment(R.layout.map), OnMapReadyCallback {
// todo @Inject constructor(private val hero: SuperHero, private val heroLocations: List<SuperHeroLocations>)
    private val binding: MapBinding by viewBinding(MapBinding::bind) // viewBinding imported fm Fragment ViewBindingDelegate.kt
//    private lateinit var binding: MapBinding
    private val viewModel: MapViewModel by activityViewModels() // was HeroViewModel
//    private val args: LocationsFragmentArgs by navArgs() // L5.. use DetailFragmentArgs?
//    private var heroLocations = listOf<SuperHeroLocations>()

    private val locationsDemo2 = listOf(
        SuperHeroLocations(id = "AB3A873C-37B4-4FDE-A50F-8014D40D94FE",36.8415268,-2.4746262),
        SuperHeroLocations(id = "E7D32AAB-8846-40DB-BF08-F4AA82B915C5",40.43,-3.97)
    )

    private lateinit var map: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        Log.d("Tag", "This LocationsFragment hero is: ${hero.name}")
        Log.w("Tag", "LocationsFragment > onViewCreated > heroLocations: ${hero.name}: $heroLocations")
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

//        binding.tvHeroNameMap.text = args.superheroId // todo update
//        binding.tvHeroNameMap = "hi"

//        binding.bShowLocations.setOnClickListener {
////            Log.d("Tag", "binding.bShowLocations.setOnClickListener...")
////            map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(40.0, -3.6), 20.0F))
////            loadLocations() // this this function on L115
//
//            if (checkPermission()) {
//                panToLocation()
//            }
//        }
    }

    @SuppressLint("MissingPermission")
    fun panToLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        fusedLocationClient.lastLocation.addOnCompleteListener{
            if (it.isSuccessful){
                val location = it.result
                val marker = MarkerOptions().position(LatLng(location.latitude, location.longitude))
                map.addMarker(marker)
            }
        }
    } // this pans map to given point, NO REAL USE FOR IT

    private fun checkPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    } // only needed to use user location

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val hasPermission = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            heroLocations = loadLocations()
            Log.d("Tag","onAttach heroLocations: $heroLocations") // prints...
        } else {
            val permissionLauncher =
                registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                    if (isGranted) {
                        // todo: getLocation
                        heroLocations = loadLocations()
                        Log.d("Tag","onAttach heroLocations: $heroLocations") // prints...
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "No podemos mostrar la localizacion sin permiso",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    } // this fun is auto called w/in itself

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        val madrid = LatLng(40.0, -3.6)
//        val barcelona = LatLng(41.4, 2.2)
//        map.addMarker(MarkerOptions().position(madrid))
//        map.addMarker(MarkerOptions().position(barcelona))

        // todo map function

//        locationsDemo2.forEach { // works
//            map.addMarker(MarkerOptions().position(LatLng(it.latitude!!, it.longitude!!)))
//        }

        Log.d("Tag","onMapReady heroLocations: $heroLocations") // prints []
        heroLocations.forEach {
            map.addMarker(MarkerOptions().position(LatLng(it.latitude!!, it.longitude!!)))
        }

//        map.addCircle(CircleOptions().center(madrid).radius(10.0).fillColor(Color.BLACK))
//        map.moveCamera(CameraUpdateFactory.newLatLng(madrid))
    }

    private fun configureObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.mapState.collect {
                when (it) {
                    is MapViewModel.MapState.OnHeroLocationReceived -> {
                        Log.d("Tag LocationsFragment", ".OnHeroLocationReceived")
                    }
                    is MapViewModel.MapState.ErrorJSON -> {
                        Log.d("Tag LocationsFragment", ".ErrorJSON")
                    }
                    is MapViewModel.MapState.ErrorResponse -> {
                        Log.d("Tag LocationsFragment", ".ErrorResponse")
                    }
                    is MapViewModel.MapState.Idle -> Unit
                }
            }
        }
    }

    private fun loadLocations(): List<SuperHeroLocations> {
        // todo: use _heroState to nav to locationsFragment
        var heroes = listOf<SuperHeroLocations>()
        activity?.getPreferences(Context.MODE_PRIVATE)?.let {
            Log.d("Tag", "loadLocations...")
            User.getToken(requireContext())?.let { token ->
//                viewModel.getLocationsX(token, hero.id)
                heroes = viewModel.getLocationsX(token, hero.id) // MUST have this to work!
//                viewModel.getLocations5(token, hero.id) // TODO: Debug this Retrofit version and use instead of getLocationX
            }
        }
        return heroes
    }
}

