package com.example.androidadvanced.ui.home.herolist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidadvanced.databinding.HeroCellBinding
import com.example.androidadvanced.ui.model.SuperHero
import com.squareup.picasso.Picasso

interface HeroAdapterCallback {
    // this input param to class HeroListFragment @Inject constructor (pri val VM: HeroVM): Fragm(), HeroAdapterCallback {
    fun heroSelectionClicked(hero: SuperHero) // was Hero, then GetHeroResponse (worked then)
}

class HeroCellAdapter( // adding
//    private val listHeroes: List<SuperHero>, // was <Hero>
    private val callback: HeroAdapterCallback, // this callback takes SuperHero from
    ): RecyclerView.Adapter<HeroCellAdapter.HeroViewHolder>() {

    private var listHeroes = listOf<SuperHero>() // moved this parameter out of definition line to here
    class HeroViewHolder(private var binding: HeroCellBinding, private val callback: HeroAdapterCallback): RecyclerView.ViewHolder(binding.root) {
        fun showHero(hero: SuperHero) { // was Hero, then was GetHeroesResponse
//            Log.d("Tag", "HeroCellAdapter > HeroViewHolder > showHero...")
//            Log.d("Tag", "SuperHero name = $hero.name")
            binding.tvHeroNameCell.text = hero.name
            Picasso.get().load(hero.photo).into(binding.ivHeroThumb) // todo: use another tool other than Picasso?
            if (hero.favorite) {
                Log.d("Tag", "${hero.name} is a favorite")
                binding.star.isChecked
                binding.star.alpha = 1.0F
            }
            binding.lLHeroCell.setOnClickListener { // makes table cell clickable
                callback.heroSelectionClicked(hero)  // calls fun w given hero
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val binding = HeroCellBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
//        Log.d("Tag", "HeroCellAdapter > onCreateViewHolder...")
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