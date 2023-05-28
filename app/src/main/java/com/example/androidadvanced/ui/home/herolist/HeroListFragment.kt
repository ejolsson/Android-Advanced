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
import com.example.androidadvanced.ui.map.LocationsFragment
import com.example.androidadvanced.ui.model.SuperHero
import com.google.android.gms.maps.MapFragment
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
//        viewModel = HeroViewModel(requireContext()) // adv class puts it here
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
    fun mapButtonClicked() {

    }
    private fun configureListeners() {
        Log.d("Tag HeroListFrag", "Favorite Heroes are... TBD")
    }
    // Below has been moved to DetailsFragment
//        private fun goToLocationsFragment() { // #2
//        (activity as? HeroActivity)?.presentLocationsFragment()
//    }
    private fun configureObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.heroListState.collect { heroListState ->
                when (heroListState) {
                    // 1
                    is HeroViewModel.HeroListState.OnHeroListReceived -> {
                        Log.d("Tag HeroListFrag", ".OnHeroListReceived")
                        Log.d("Tag HeroListFrag", "HeroListFrag > onViewCreated > List<SuperHeroes> = ${heroListState.heroes2.first()}") // print successful
                        showHeroes(heroListState.heroes2)
                    }
                    // 2
                    is HeroViewModel.HeroListState.OnHeroSelected -> { // navigate to clicked-on hero
                        Log.d("Tag HeroListFrag", ".OnHeroSelected")
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fFragment, DetailsFragment(viewModel, heroListState.hero)) // input parameters must match class constructor
                            .addToBackStack(HeroActivity::javaClass.name)
                            .commit()
                    }
//                    is HeroViewModel.HeroListState.OnHeroLocationReceived -> {
//                        Log.w("Tag HeroListFrag", ".OnHeroListReceived")
//                    }
                    // 3
                    is HeroViewModel.HeroListState.OnHeroesUpdated -> {
                        Log.d("Tag HeroListFrag", ".OnHeroesUpdated")
                        adapter.notifyDataSetChanged()
                    }
//                    // 4
//                    is HeroViewModel.HeroListState.OnMapSelected -> { // #3
//                        Log.d("Tag HeroListFrag", ".OnMapSelected")
//                        // todo doesn't print... b/c HeroListFragment not active!!!
//                        goToLocationsFragment()
////                        parentFragmentManager.beginTransaction()
////                            .replace(R.id.fFragment, LocationsFragment()) //
////                            .addToBackStack(HeroActivity::javaClass.name)
////                            .commit()
//                    }
                    // 5
                    is HeroViewModel.HeroListState.ErrorJSON ->
                        Log.d("Tag HeroListFrag", "HeroState ErrorJSON")
                    // 6
                    is HeroViewModel.HeroListState.ErrorResponse ->
                        Log.d("Tag HeroListFrag", "HeroState ErrorResponse")
                    // 7
                    is HeroViewModel.HeroListState.Idle -> Unit
                }
            }
        }
    }
}