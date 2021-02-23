package com.example.hilttraining.ui

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.hilttraining.data.Heroes
import com.example.hilttraining.databinding.ItemListBinding
import javax.inject.Inject

class MainAdapter @Inject constructor() : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private val heroList = mutableListOf<Heroes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(heroList[position])
    }

    override fun getItemCount(): Int {
        return heroList.size
    }

    fun updateItems(heroes: List<Heroes>) {
        heroes.forEach {
            heroList.add(it)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(hero: Heroes) {
            binding.heroName.text = hero.localized_name
            val roles = mutableListOf<String>()
            for (i in hero.roles.indices) {
                roles.add(hero.roles[i])
            }
            binding.heroRoles.text = roles.toString()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemListBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}