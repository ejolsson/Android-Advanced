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

class DetailsFragment(private val hero: SuperHero) : Fragment() { // was Hero

    private lateinit var binding: DetailsBinding
    private val viewModel: HeroViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsBinding.inflate(inflater)
        binding.tvHeroDetailTitle.text = hero.name
        binding.tvHeroDescription.text = hero.description
        Picasso.get().load(hero.photo).into(binding.ivHeroDetailPic)
        Log.d("Tag DetailsFrag", "FightFrag > onCreateView ********")
        setObservers()
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val makeFavoriteButton = getView()?.findViewById<Button>(R.id.bMakeFavorite)

        Log.d("Tag DetailsFrag", "${hero.name}")

        makeFavoriteButton?.setOnClickListener {
            Log.d("Tag DetailsFrag", "FightFrag > ${hero.name}")
            viewModel.returnToHeroList() // works. pulled logic out of if (hero.currentLife <= 0) {}
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