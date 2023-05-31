package com.example.androidadvanced.ui.details

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidadvanced.data.User
import com.example.androidadvanced.databinding.DetailsBinding
import com.example.androidadvanced.ui.home.HeroActivity
import com.example.androidadvanced.ui.home.HeroViewModel
import com.example.androidadvanced.ui.map.MapViewModel
import com.example.androidadvanced.ui.model.SuperHero
import com.example.androidadvanced.ui.model.SuperHeroLocations
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsFragment @Inject constructor(private val viewModel: HeroViewModel, private var hero: SuperHero) : Fragment() {

    private lateinit var binding: DetailsBinding
    private val mapViewModel: MapViewModel by activityViewModels() // TODO: migrate mapViewModel "loadLocations" to HeroViewModel
    private var heroLocations = listOf<SuperHeroLocations>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsBinding.inflate(inflater)
        binding.tvHeroDetailTitle.text = hero.name
        binding.tvHeroDescription.text = hero.description
        Picasso.get().load(hero.photo).into(binding.ivHeroDetailPic)
        Log.d("Tag DetailsFrag", "DetailsFrag > makeFavoriteButton ${hero.name} favorite status is: ${hero.favorite}")
        configureObservers()
        configureListeners()
        heroLocations = loadLocations()
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun configureListeners() {

        binding.bMakeFavorite.setOnClickListener {
            viewModel.makeHeroFavorite(hero)
        }

        binding.bShowLocations.setOnClickListener {
            viewModel.goToMapPage(hero, heroLocations)
        }
    }
    private fun loadLocations(): List<SuperHeroLocations> {
        // todo: use _heroState to nav to locationsFragment
        var locations = listOf<SuperHeroLocations>()
        activity?.getPreferences(Context.MODE_PRIVATE)?.let {
//            Log.d("Tag", "loadLocations...")
            User.getToken(requireContext())?.let { token ->
//                viewModel.getLocationsX(token, hero.id)
                locations = mapViewModel.getLocationsX(token, hero.id) // lateinit property locationsLiving has not been initialized
//                viewModel.getLocations5(token, id) // TODO: Debug this Retrofit version and use instead of getLocationX
//                Log.d("Tag", "heroLocations (new output): $locations") // has been [] for many cycles now...
            }
        }
        return locations
    }
    private fun goToLocationsFragment(hero: SuperHero, locations: List<SuperHeroLocations>) {
        (activity as? HeroActivity)?.presentLocationsFragment(hero, locations)
    }
    private fun configureObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.detailState.collect { detailState ->
                when (detailState) {
                    // 4
                    is HeroViewModel.DetailState.OnMapSelected -> {
                        goToLocationsFragment(hero, heroLocations)
                    }
                    is HeroViewModel.DetailState.Idle -> Unit
                }
            }
        }
    }
}