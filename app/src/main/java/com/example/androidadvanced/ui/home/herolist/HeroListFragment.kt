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
import com.example.androidadvanced.ui.home.SharedViewModel
import com.example.androidadvanced.ui.details.DetailsFragment
import com.example.androidadvanced.data.Hero
import com.example.androidadvanced.data.remote.response.GetHeroesResponse
import kotlinx.coroutines.launch

class HeroListFragment(): Fragment(), HeroClicked {

    private lateinit var binding: HeroListFragmentBinding
    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var adapter: HeroCellAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HeroListFragmentBinding.inflate(inflater)
        Log.w("Tag HeroListFrag", "HeroListFrag > onCreateView...")
        setObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch{
            viewModel.heroListState.collect {
                when(it) {
                    is SharedViewModel.HeroListState.OnHeroListReceived -> {
                        Log.w("Tag HeroListFrag", ".OnHeroListReceived")
//                        Log.w("Tag HeroListFrag", "HeroListFrag > onViewCreated > heroesFight = ${it.heroes}")
//                        it.heroes[0].currentLife = 0 // this removes Broly by forcing her currentLife to 0
//                        adapter = HeroCellAdapter(it.heroes.filter { it.currentLife > 0 }, this@HeroListFragment)
                        adapter = HeroCellAdapter(it.heroes2, this@HeroListFragment) // removed life filter, toggle heroes & heroes2
                        binding.rvListOfHeroes.layoutManager = LinearLayoutManager(binding.root.context)
                        binding.rvListOfHeroes.adapter = adapter
                    }
                    is SharedViewModel.HeroListState.OnHeroDeath -> { // todo: remove this state
                        Log.w("Tag HeroListFrag", ".OnHeroDeath")
                        adapter = HeroCellAdapter(it.heroes, this@HeroListFragment) // updated fm (it.heroes.filter { it.currentLife > 0 },...
                        // todo: update the viewModel list instead of creating another adapter.
                        binding.rvListOfHeroes.layoutManager = LinearLayoutManager(binding.root.context)
                        binding.rvListOfHeroes.adapter = adapter
//                        Log.w("Tag HeroListFrag", "HeroListFrag > is ....OnHero Death > heroesLiving = ${viewModel.heroesLiving}")
                    }
                    is SharedViewModel.HeroListState.ErrorJSON ->
                        Log.w("Tag HeroListFrag", "HeroState ErrorJSON")
                    is SharedViewModel.HeroListState.ErrorResponse ->
                        Log.w("Tag HeroListFrag", "HeroState ErrorResponse")
                    is SharedViewModel.HeroListState.Idle -> Unit
                    is SharedViewModel.HeroListState.OnHeroSelected -> { // navigate to clicked-on hero
                        Log.w("Tag HeroListFrag", ".OnHeroSelected")
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fFragment, DetailsFragment(it.hero))
                            .addToBackStack(HeroActivity::javaClass.name)
                            .commit()
                    }
                }
            }
        }
    }

    override fun heroSelectionClicked(hero: GetHeroesResponse) { // was Hero
        viewModel.selectHero(hero)
    }
}