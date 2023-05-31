package com.example.androidadvanced.ui.home.herolist

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidadvanced.R
import com.example.androidadvanced.data.User
import com.example.androidadvanced.databinding.HeroListFragmentBinding
import com.example.androidadvanced.ui.home.HeroActivity
import com.example.androidadvanced.ui.home.HeroViewModel
import com.example.androidadvanced.ui.details.DetailsFragment
import com.example.androidadvanced.ui.model.SuperHero
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HeroListFragment @Inject constructor(private val viewModel: HeroViewModel): Fragment(), HeroAdapterCallback {

    private lateinit var binding: HeroListFragmentBinding
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
        Log.w("Tag","Change log: ____")
        configureAdapter()
        configureObservers()
        configureListeners()
        loadHeroes()
    }

    private fun configureAdapter() {
        Log.d("Tag", "HeroListFrag > configureAdapter...")
        with(binding){
            rvListOfHeroes.layoutManager = LinearLayoutManager(requireContext())
            rvListOfHeroes.adapter = adapter
        }
    }

    private fun loadHeroes() {
        activity?.getPreferences(Context.MODE_PRIVATE)?.let {
            User.getToken(requireContext())?.let {token ->
                viewModel.getHeroes5(token)
            }
        }
    }

    private fun showHeroes(heroes: List<SuperHero>) {
        adapter.updateList(heroes)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    override fun heroSelectionClicked(hero: SuperHero) { // was Hero
        viewModel.selectHero(hero)
    }

    private fun configureListeners() {
        Log.d("Tag HeroListFrag", "Favorite Heroes are... TBD")
    }

    private fun configureObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.heroListState.collect { heroListState ->
                when (heroListState) {
                    is HeroViewModel.HeroListState.OnHeroListReceived -> {
                        Log.d("Tag HeroListFrag", "HeroListFrag > onViewCreated > List<SuperHeroes> = ${heroListState.heroes2.first()}")
                        showHeroes(heroListState.heroes2)
                    }
                    is HeroViewModel.HeroListState.OnHeroSelected -> { // navigate to clicked-on hero
                        Log.d("Tag HeroListFrag", ".OnHeroSelected")
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fFragment, DetailsFragment(viewModel, heroListState.hero))
                            .addToBackStack(HeroActivity::javaClass.name)
                            .commit()
                    }
                    is HeroViewModel.HeroListState.OnHeroesUpdated -> {
                        Log.d("Tag HeroListFrag", ".OnHeroesUpdated")
                        adapter.notifyDataSetChanged()
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
}