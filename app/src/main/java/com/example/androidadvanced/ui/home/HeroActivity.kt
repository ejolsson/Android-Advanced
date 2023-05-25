package com.example.androidadvanced.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.androidadvanced.R
import com.example.androidadvanced.databinding.HeroActivityBinding
import com.example.androidadvanced.ui.home.herolist.HeroListFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
//class HeroActivity @Inject constructor(private var viewModel: HeroViewModel) : AppCompatActivity() {
class HeroActivity : AppCompatActivity() {
    companion object {
        const val TAG_TOKEN = "TAG_TOKEN"
        fun launch(context: Context, token: String) {
            val intent = Intent(context, HeroActivity::class.java)
            intent.putExtra(TAG_TOKEN, token)
            context.startActivity(intent) // w/o this, can't move past login
        }
    } // connects to LoginAct > onCreate > lifecycleScope.launch > when is LoginViewModel.LoginState.OnLoginReceived

    //    @Inject
//    lateinit var viewModel: HeroViewModel
    private val viewModel: HeroViewModel by viewModels()

    private lateinit var binding: HeroActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HeroActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presentHeroesListFragment()
//        intent.extras?.getString(TAG_TOKEN, "")?.let { token ->
//            sharedViewModel.fetchHeroes(token) // v1 API call // has been commented out
//            sharedViewModel.getHeroes() // v2 API call, hero list now rendering, todo: add token parameter // todo: remove or move to Fragment
//        }

        val testButton = findViewById<Button>(R.id.bTest)
        testButton.setOnClickListener {
            Log.d("Tag","Test button clicked")
            deleteHeroes()
//            Log.d("Tag", "${viewModel}")
        }
    }
    private fun presentHeroesListFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fFragment.id, HeroListFragment(viewModel))
            .commitNow() // was .commitNow()
    }
    private fun deleteHeroes() {
        viewModel.deleteHeroes5()
    }
    // todo: add presentHeroesDetailsFragment()
//    fun presentHeroesDetailsFragment() {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(binding.fFragment.id, DetailsFragment(hero: SuperHero))
//            .addToBackStack(null)
//            .commit()
//    }
}