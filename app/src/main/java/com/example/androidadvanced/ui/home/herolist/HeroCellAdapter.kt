package com.example.androidadvanced.ui.home.herolist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidadvanced.databinding.HeroCellBinding
import com.example.androidadvanced.ui.model.SuperHero
import com.squareup.picasso.Picasso

interface HeroAdapterCallback {
    fun heroSelectionClicked(hero: SuperHero)
}

class HeroCellAdapter(
    private val callback: HeroAdapterCallback,
    ): RecyclerView.Adapter<HeroCellAdapter.HeroViewHolder>() {

    private var listHeroes = listOf<SuperHero>()
    class HeroViewHolder(private var binding: HeroCellBinding, private val callback: HeroAdapterCallback): RecyclerView.ViewHolder(binding.root) {
        fun showHero(hero: SuperHero) {
            binding.tvHeroNameCell.text = hero.name
            Picasso.get().load(hero.photo).into(binding.ivHeroThumb)
            if (hero.favorite) {
                Log.d("Tag", "${hero.name} is a favorite")
                binding.star.isChecked
                binding.star.alpha = 1.0F
            }
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