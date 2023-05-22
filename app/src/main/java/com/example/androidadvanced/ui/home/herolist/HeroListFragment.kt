package com.example.androidadvanced.ui.home.herolist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidadvanced.R
import com.example.androidadvanced.databinding.HeroListFragmentBinding
import com.example.androidadvanced.ui.home.HeroActivity
import com.example.androidadvanced.ui.home.HeroViewModel
import com.example.androidadvanced.ui.details.DetailsFragment
import com.example.androidadvanced.ui.model.SuperHero
import kotlinx.coroutines.launch

class HeroListFragment(): Fragment(), HeroAdapterCallback {

    private lateinit var binding: HeroListFragmentBinding // not showing during runtime
    private lateinit var viewModel: HeroViewModel
    private var adapter = HeroCellAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HeroListFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = HeroViewModel(requireContext()) // adv class puts it here
        Log.d("Tag", "HeroListFragment onViewCreated...") // works, fragment is being called
        configureAdapter() // issues
        configureObservers()
        configureListeners()
        loadHeroes()
    }

    private fun configureListeners() {
        // todo: add favorite button
        Log.d("Tag HeroListFrag", "Favorite Heroes are... TBD")
    }
    private fun configureObservers() {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.heroListState.collect {
                    when (it) {
                        is HeroViewModel.HeroListState.OnHeroListReceived -> {
                            Log.d("Tag HeroListFrag", ".OnHeroListReceived")
                            Log.w("Tag HeroListFrag", "HeroListFrag > onViewCreated > heroes = ${it.heroes2}")

                            adapter = HeroCellAdapter(
//                                it.heroes2, // todo: needed to pass heroes?
                                this@HeroListFragment
                            ) // removed life filter, toggle heroes & heroes2
                            binding.rvListOfHeroes.layoutManager =
                                LinearLayoutManager(binding.root.context)
                            binding.rvListOfHeroes.adapter = adapter
                        }
                        is HeroViewModel.HeroListState.OnHeroSelected -> { // navigate to clicked-on hero
                            Log.d("Tag HeroListFrag", ".OnHeroSelected")
                            parentFragmentManager.beginTransaction()
                                .replace(R.id.fFragment, DetailsFragment(it.hero))
                                .addToBackStack(HeroActivity::javaClass.name)
                                .commit()
                        }
                        is HeroViewModel.HeroListState.OnHeroesUpdated -> {
                            Log.d("Tag HeroListFrag", ".OnHeroesUpdated")
                        }
                        is HeroViewModel.HeroListState.ErrorJSON ->
                            Log.d("Tag HeroListFrag", "HeroState ErrorJSON")
                        is HeroViewModel.HeroListState.ErrorResponse ->
                            Log.d("Tag HeroListFrag", "HeroState ErrorResponse")
                        is HeroViewModel.HeroListState.Idle -> Unit
                    }
                }
            }
        }

    private fun configureAdapter() {
        with(binding){
            rvListOfHeroes.layoutManager = LinearLayoutManager(requireContext())
            rvListOfHeroes.adapter = adapter
        }
    }

    private fun loadHeroes() {
        viewModel.getHeroes5()
        Log.d("Tag","${viewModel.getHeroes5()}")
    }
    override fun heroSelectionClicked(hero: SuperHero) { // was Hero
            viewModel.selectHero(hero)
        }

}