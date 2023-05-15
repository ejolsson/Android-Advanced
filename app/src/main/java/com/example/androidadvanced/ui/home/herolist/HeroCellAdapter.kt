package com.example.androidadvanced.ui.home.herolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidadvanced.databinding.HeroCellBinding
import com.example.androidadvanced.data.Hero
import com.example.androidadvanced.data.remote.response.GetHeroesResponse
import com.squareup.picasso.Picasso

interface HeroClicked {
    fun heroSelectionClicked(hero: GetHeroesResponse) // was Hero
}

class HeroCellAdapter(
    private val listHeroes: List<GetHeroesResponse>, // was <Hero>
    private val callback: HeroClicked,
    ): RecyclerView.Adapter<HeroCellAdapter.MainActivityViewHolder>() {

    class MainActivityViewHolder(private var item: HeroCellBinding, private val callback: HeroClicked): RecyclerView.ViewHolder(item.root) {
        fun showHero(hero: GetHeroesResponse) { // was Hero
            item.tvHeroNameCell.text = hero.name
            Picasso.get().load(hero.photo).into(item.ivHeroThumb)
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