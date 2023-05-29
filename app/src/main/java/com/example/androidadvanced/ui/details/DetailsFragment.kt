package com.example.androidadvanced.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.androidadvanced.databinding.DetailsBinding
import com.example.androidadvanced.ui.home.HeroActivity
import com.example.androidadvanced.ui.home.HeroViewModel
import com.example.androidadvanced.ui.model.SuperHero
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsFragment @Inject constructor(private val viewModel: HeroViewModel, private var hero: SuperHero) : Fragment() {

    private lateinit var binding: DetailsBinding

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
            viewModel.goToMapPage(hero)
        }
    }
    private fun goToLocationsFragment(hero: SuperHero) {
        (activity as? HeroActivity)?.presentLocationsFragment(hero)
    }
    private fun configureObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.detailState.collect { detailState ->
                when (detailState) {
                    // 4
                    is HeroViewModel.DetailState.OnMapSelected -> {
                        goToLocationsFragment(hero)
                    }
                    is HeroViewModel.DetailState.Idle -> Unit
                }
            }
        }
    }
}