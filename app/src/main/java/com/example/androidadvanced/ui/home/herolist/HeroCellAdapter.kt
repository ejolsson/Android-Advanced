package com.example.androidadvanced.ui.home.herolist

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
    ): RecyclerView.Adapter<HeroCellAdapter.MainActivityViewHolder>() {

    private var listHeroes = listOf<SuperHero>() // moved this parameter out of definition line to here

    class MainActivityViewHolder(private var item: HeroCellBinding, private val callback: HeroAdapterCallback): RecyclerView.ViewHolder(item.root) {
        fun showHero(hero: SuperHero) { // was Hero, then was GetHeroesResponse
            item.tvHeroNameCell.text = hero.name
            Picasso.get().load(hero.photo).into(item.ivHeroThumb) // todo: use another tool other than Picasso?
            item.lLHeroCell.setOnClickListener {
                callback.heroSelectionClicked(hero)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityViewHolder {
        val binding = HeroCellBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainActivityViewHolder(binding, callback)
    }
    override fun getItemCount(): Int {
        return listHeroes.size
    }

    override fun onBindViewHolder(holder: MainActivityViewHolder, position: Int) {
        holder.showHero(listHeroes[position])
    }
}