package com.example.androidadvanced.ui.details

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.androidadvanced.R
import com.example.androidadvanced.data.User
import com.example.androidadvanced.databinding.DetailsBinding
import com.example.androidadvanced.databinding.HeroActivityBinding
import com.example.androidadvanced.ui.home.HeroActivity
import com.example.androidadvanced.ui.home.HeroViewModel
import com.example.androidadvanced.ui.home.herolist.HeroListFragment
import com.example.androidadvanced.ui.map.LocationsFragment
import com.example.androidadvanced.ui.model.SuperHero
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import java.security.AccessController.checkPermission
import javax.inject.Inject

class DetailsFragment @Inject constructor(private val viewModel: HeroViewModel, private var hero: SuperHero) : Fragment() { // tried to add private val heroActivityBinding: HeroActivityBinding

    private lateinit var binding: DetailsBinding
//    private lateinit var heroActivityBinding: HeroActivityBinding // produces error: lateinit property heroActivityBinding has not been initialized
//    private val viewModel: HeroViewModel by activityViewModels()
//    private lateinit var viewModel: HeroViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        viewModel = HeroViewModel(requireContext()) // onViewCreated has issues, trying here.. it works!
        binding = DetailsBinding.inflate(inflater)
        binding.tvHeroDetailTitle.text = hero.name
        binding.tvHeroDescription.text = hero.description
        Picasso.get().load(hero.photo).into(binding.ivHeroDetailPic)
        Log.d("Tag DetailsFrag", "DetailsFrag > makeFavoriteButton ${hero.name} favorite status is: ${hero.favorite}")
        configureObservers()
        configureListeners()
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = HeroViewModel(requireContext()) // lateinit property viewModel has not been initialized

    }

    private fun configureListeners() {

        binding.bMakeFavorite.setOnClickListener {
            viewModel.makeHeroFavorite(hero)
        }

        binding.bShowLocations.setOnClickListener { // #6 of 6
            Log.d("Tag", "#6 Map button pressed") // prints ok
            viewModel.goToMapPage(hero) // takes hero: SuperHero param of DetailsFragment
        }
    }
    private fun goToLocationsFragment() { // #2
        Log.d("Tag DetailsFrag", "#2 goToLocationsFragment") // gtg
        (activity as? HeroActivity)?.presentLocationsFragment()
    }
    private fun configureObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.detailState.collect { detailState ->
                when (detailState) {
                    // 4
                    is HeroViewModel.DetailState.OnMapSelected -> { // #3
                        Log.d("Tag DetailsFrag", "#3 .OnMapSelected") // gtg
                        goToLocationsFragment()
//                        parentFragmentManager.beginTransaction()
//                            .replace(R.id.fFragment, LocationsFragment()) //
//                            .addToBackStack(HeroActivity::javaClass.name)
//                            .commit()
                    }
                    is HeroViewModel.DetailState.Idle -> Unit
                }
            }
        }
    }
}