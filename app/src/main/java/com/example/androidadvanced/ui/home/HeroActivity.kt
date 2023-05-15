package com.example.androidadvanced.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.androidadvanced.databinding.HeroActivityBinding
import com.example.androidadvanced.ui.home.herolist.HeroListFragment

class HeroActivity : AppCompatActivity() {

    companion object {

        const val TAG_TOKEN = "TAG_TOKEN"
        fun launch(context: Context, token: String) {
            val intent = Intent(context, HeroActivity::class.java)
            intent.putExtra(TAG_TOKEN, token)
//            Log.w("Tag HeroActivity","companion token = $token")
            context.startActivity(intent)
        }
    }

    private lateinit var heroActivityBinding: HeroActivityBinding
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.w("Tag HeroActivity", "HeroAct > onCreate...")
        super.onCreate(savedInstanceState)
        heroActivityBinding = HeroActivityBinding.inflate(layoutInflater)
        setContentView(heroActivityBinding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(heroActivityBinding.fFragment.id, HeroListFragment())
            .commitNow()
        intent.extras?.getString(TAG_TOKEN, "")?.let { token ->
            sharedViewModel.fetchHeroes(token)
//            sharedViewModel.getHeroes() // hero list blank
        }
    }
}