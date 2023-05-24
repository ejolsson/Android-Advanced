package com.example.androidadvanced.ui.home.herolist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidadvanced.databinding.HeroCellBinding
import com.example.androidadvanced.ui.model.SuperHero
import com.squareup.picasso.Picasso

interface HeroAdapterCallback {
    fun heroSelectionClicked(hero: SuperHero) // was Hero, then GetHeroResponse (worked then)
}

class HeroCellAdapter( // adding
//    private val listHeroes: List<SuperHero>, // was <Hero>
    private val callback: HeroAdapterCallback, // this callback takes SuperHero from
    ): RecyclerView.Adapter<HeroCellAdapter.HeroViewHolder>() {

    private var listHeroes = listOf<SuperHero>() // moved this parameter out of definition line to here
    // todo: ?? How is the hero data getting here??
    class HeroViewHolder(private var binding: HeroCellBinding, private val callback: HeroAdapterCallback): RecyclerView.ViewHolder(binding.root) {
        fun showHero(hero: SuperHero) { // was Hero, then was GetHeroesResponse
            Log.d("Tag", "HeroCellAdapter > HeroViewHolder > showHero...")
            Log.d("Tag", "SuperHero name = $hero.name")
            binding.tvHeroNameCell.text = hero.name
            Picasso.get().load(hero.photo).into(binding.ivHeroThumb) // todo: use another tool other than Picasso?
            binding.lLHeroCell.setOnClickListener {
                callback.heroSelectionClicked(hero)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val binding = HeroCellBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        Log.d("Tag", "HeroCellAdapter > onCreateViewHolder...")
        return HeroViewHolder(binding, callback)
    }
    override fun getItemCount(): Int {
        return listHeroes.size
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.showHero(listHeroes[position])
    }

    fun updateList(list: List<SuperHero>) {
        listHeroes = list
        notifyDataSetChanged()
    }
}