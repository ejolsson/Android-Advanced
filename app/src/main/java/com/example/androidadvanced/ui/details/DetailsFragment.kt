package com.example.androidadvanced.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidadvanced.R
import com.example.androidadvanced.databinding.DetailsBinding
import com.example.androidadvanced.ui.home.HeroViewModel
import com.example.androidadvanced.ui.model.SuperHero
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsFragment @Inject constructor(private val viewModel: HeroViewModel, private var hero: SuperHero) : Fragment() { // was Hero

    private lateinit var binding: DetailsBinding
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
        setObservers()
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = HeroViewModel(requireContext()) // lateinit property viewModel has not been initialized
        val makeFavoriteButton = getView()?.findViewById<Button>(R.id.bMakeFavorite)

        makeFavoriteButton?.setOnClickListener {
            viewModel.makeHeroFavorite(hero)
            hero.favorite = true
            binding.cbHeroDetail.alpha = 1.0F
            Log.d("Tag DetailsFrag", "${hero.name} favorite status after: ${hero.favorite}")
        }
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.heroState.collect {
                when (it) {
                    is HeroViewModel.HeroState.OnHeroReceived -> {
                        Log.d("Tag DetailsFrag", ".OnHeroReceived")
                    }
                    is HeroViewModel.HeroState.HeroLifeZero -> {
                        Log.d("Tag DetailsFrag", ".HeroLifeZero")
//                        Log.w("Tag DetailsFrag", "Shared ViewModel > getHeroes() > heroesLiving = ${viewModel.heroesLiving}")
                        activity?.supportFragmentManager?.popBackStack()
                    }
                    is HeroViewModel.HeroState.Idle -> Unit
                }
            }
        }
    }
}