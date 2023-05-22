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

    private lateinit var binding: HeroListFragmentBinding


//    private val viewModel: HeroViewModel by activityViewModels() // lateinit property adapter has not been initialized
    // 20230521 20:54 CET Cannot create an instance of class HeroViewModel... action: comment out. Try below
    // AndFund, AndroidAvanzado Day5, Carlos Sol use this
    // 20230521 21:17 CET back to this, 21:17 Cannot create an instance of class HeroViewModel

    private lateinit var viewModel: HeroViewModel // was private val viewModel: HeroViewModel by activityViewModels()
    // 20230521 21:03 CET now using lateinit... 21:04 Result: back to lateinit property viewModel has not been initialized
    // AndroidAvanzado Day 3 uses this

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
        configureAdapter() // issues
        configureObservers()
        configureListeners()
        loadHeroes()
    }

    /*
    From onCreateView > View (Fundamentals project)

    intent.extras?.getString(HeroActivity.TAG_TOKEN, "")?.let { token ->
    viewModel.getHeroes5() // v2 API call,
     */

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

//                        Log.w("Tag HeroListFrag", "HeroListFrag > onViewCreated > heroesFight = ${it.heroes}")
//                        it.heroes[0].currentLife = 0 // this removes Broly by forcing her currentLife to 0
//                        adapter = HeroCellAdapter(it.heroes.filter { it.currentLife > 0 }, this@HeroListFragment)

                            adapter = HeroCellAdapter(
//                                it.heroes2,
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

//                        is HeroViewModel.HeroListState.OnHeroDeath -> { // todo: remove this state
//                            Log.w("Tag HeroListFrag", ".OnHeroDeath")
//                            adapter = HeroCellAdapter(
//                                it.heroes,
//                                this@HeroListFragment
//                            ) // updated fm (it.heroes.filter { it.currentLife > 0 },...
//                            // todo: update the viewModel list instead of creating another adapter.
//                            binding.rvListOfHeroes.layoutManager =
//                                LinearLayoutManager(binding.root.context)
//                            binding.rvListOfHeroes.adapter = adapter
////                        Log.w("Tag HeroListFrag", "HeroListFrag > is ....OnHero Death > heroesLiving = ${viewModel.heroesLiving}")
//                        } // todo REMOVE

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
    }
    override fun heroSelectionClicked(hero: SuperHero) { // was Hero
            viewModel.selectHero(hero)
        }

}