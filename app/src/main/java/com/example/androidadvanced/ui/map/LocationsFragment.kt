package com.example.androidadvanced.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.androidadvanced.ui.details.DetailsFragment
import com.example.androidadvanced.ui.home.HeroActivity
import com.example.androidadvanced.ui.home.HeroViewModel
import com.example.androidadvanced.utils.viewBinding
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.launch
//import java.security.AccessController.checkPermission // suspect causing an error with "checkPermission()"
import javax.inject.Inject

class LocationsFragment: Fragment(R.layout.map), OnMapReadyCallback {

    private val binding: MapBinding by viewBinding(MapBinding::bind) // viewBinding imported fm Fragment ViewBindingDelegate.kt
    private val viewModel: MapViewModel by activityViewModels() // was HeroViewModel
//    private val args: LocationsFragmentArgs by navArgs() // L5.. use DetailFragmentArgs?

    private lateinit var map: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

//        binding.tvHeroNameMap.text = args.superheroId // todo update

        binding.bShowLocations.setOnClickListener {
            Log.w("Tag", "binding.bShowLocations.setOnClickListener...")
//            map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(40.0, -3.6), 20.0F))

            loadLocations()

            if (checkPermission()) {
                showLocation()
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun showLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        fusedLocationClient.lastLocation.addOnCompleteListener{
            if (it.isSuccessful){
                val location = it.result
                val marker = MarkerOptions().position(LatLng(location.latitude, location.longitude))
//                fFragment.addMarker(marker)
            }
        }
    }

    private fun checkPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

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
//            loadLocations()
        } else {
            val permissionLauncher =
                registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                    if (isGranted) {
                        // todo: getLocation
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
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        val madrid = LatLng(40.0, -3.6)
        map.addMarker(
            MarkerOptions()
                .position(madrid)
        )
//        map.addPolyline(PolylineOptions().addAll(listOf(sydney, madrid)).color(Color.BLUE).width(100F))
//        map.setOnMarkerClickListener {
//            binding.customMarker.text = "Sydney"
//            binding.customMarker.isVisible = true
//            true
//        }
        map.addCircle(CircleOptions().center(madrid).radius(10.0).fillColor(Color.BLACK))
        map.moveCamera(CameraUpdateFactory.newLatLng(madrid))
    }

    private fun configureObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.mapState.collect {
                when (it) {
                    is MapViewModel.MapState.OnHeroLocationReceived -> {
                        Log.d("Tag LocationsFragment", ".OnHeroLocationReceived")
                    }
                    is MapViewModel.MapState.Idle -> Unit
                }
            }
        }
    }

    private fun loadLocations() {
        // todo: use _heroState to nav to locationsFragment
        activity?.getPreferences(Context.MODE_PRIVATE)?.let {
            Log.w("Tag", "loadLocations...")
            User.getToken(requireContext())?.let { token ->
                viewModel.getLocations5(token)
            }
        }
    }
}

