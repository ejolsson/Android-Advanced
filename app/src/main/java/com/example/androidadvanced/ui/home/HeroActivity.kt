package com.example.androidadvanced.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.androidadvanced.databinding.HeroActivityBinding
import com.example.androidadvanced.ui.home.herolist.HeroListFragment
import com.example.androidadvanced.ui.map.LocationsFragment
import com.example.androidadvanced.ui.model.SuperHero
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroActivity : AppCompatActivity() {
    companion object {
        const val TAG_TOKEN = "TAG_TOKEN"
        fun launch(context: Context, token: String) {
            val intent = Intent(context, HeroActivity::class.java)
            intent.putExtra(TAG_TOKEN, token)
            context.startActivity(intent)
        }
        fun start(context: Context) {
            val intent = Intent(context, HeroActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val viewModel: HeroViewModel by viewModels()

    private lateinit var binding: HeroActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HeroActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presentHeroesListFragment()
    }
    private fun presentHeroesListFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fFragment.id, HeroListFragment(viewModel))
//            .addToBackStack(HeroActivity::javaClass.name)
            .commitNow()
    }
    private fun deleteHeroes() {
        viewModel.deleteHeroes5()
    }

    fun presentLocationsFragment(hero: SuperHero) {
        Log.d("Tag HeroAct", "#1 presentLocationsFragment")
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fFragment.id, LocationsFragment(hero))
//            .addToBackStack(HeroActivity::javaClass.name)
            .commitNow()
    }
}