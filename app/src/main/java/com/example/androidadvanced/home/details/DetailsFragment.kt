package com.example.androidadvanced.home.details

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
import com.example.androidadvanced.home.SharedViewModel
import com.example.androidfundamentals.data.Hero
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class DetailsFragment(private val hero: Hero) : Fragment() {

    private lateinit var binding: DetailsBinding
    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsBinding.inflate(inflater)
        binding.tvHeroDetailTitle.text = hero.name
        binding.tvHeroDescription.text = hero.description
        Picasso.get().load(hero.photo).into(binding.ivHeroDetailPic)
        Log.w("Tag FightFrag", "FightFrag > onCreateView ********")
        setObservers()
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val makeFavoriteButton = getView()?.findViewById<Button>(R.id.bMakeFavorite)

        Log.w("Tag FightFrag", "${hero.name} life: ${hero.currentLife}")

        makeFavoriteButton?.setOnClickListener {
            Log.w("Tag FightFrag", "FightFrag > ${hero.name}")
            if (hero.currentLife <= 0)  {
                Log.w("Tag FightFrag", "Life <= 0")
                viewModel.returnToHeroList()
            }
        }
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.heroState.collect {
                when (it) {
                    is SharedViewModel.HeroState.OnHeroReceived -> {
                        Log.w("Tag FightFrag", ".OnHeroReceived")
                    }
                    is SharedViewModel.HeroState.HeroLifeZero -> {
                        Log.w("Tag FightFrag", ".HeroLifeZero")
                        Log.w("Tag FightFrag", "Shared ViewModel > getHeroes() > heroesLiving = ${viewModel.heroesLiving}")
                        activity?.supportFragmentManager?.popBackStack()
                    }
                    is SharedViewModel.HeroState.Idle -> Unit
                }
            }
        }
    }
}