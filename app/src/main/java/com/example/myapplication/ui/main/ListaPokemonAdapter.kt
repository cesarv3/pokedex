package com.example.myapplication.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myapplication.R
import com.example.myapplication.data.model.Pokemon
import kotlinx.android.synthetic.main.item_pokemon.view.*

class ListaPokemonAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listadoPokemones : MutableList<Pokemon> = ArrayList()
    private var context = context



    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

//        val fotoImageView =  itemView.findViewById<ImageView>(R.id.fotoPokemon)
//        val nombreTextView = itemView.findViewById<TextView>(R.id.nombrePokemon)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pokemon:Pokemon = listadoPokemones[position]
        holder.itemView.nombrePokemon.text = pokemon.name


        Glide.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/"+pokemon.getId2()+".png")
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.itemView.fotoPokemon)

    }

    override fun getItemCount(): Int {
        return listadoPokemones.size
    }

    fun agregarPokemones(listaPokemon: MutableList<Pokemon>) {
        listadoPokemones.addAll(listaPokemon)
        notifyDataSetChanged()

    }

}