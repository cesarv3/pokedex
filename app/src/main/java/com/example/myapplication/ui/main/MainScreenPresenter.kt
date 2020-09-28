package com.example.myapplication.ui.main

import android.content.Context
import android.util.Log
import com.example.myapplication.data.api.PokeapiService
import com.example.myapplication.data.model.Pokemon
import com.example.myapplication.data.model.PokemonRespuesta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainScreenPresenter(val view : MainScreenContract.View):MainScreenContract.Presenter {

//    override fun obtenerDatos(): MutableList<Pokemon>? {
//        var bandera = false;
//        var listaPokemon:MutableList<Pokemon>? = ArrayList()
//        val retrofit = Retrofit.Builder().baseUrl("http://pokeapi.co/api/v2/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val service = retrofit.create(PokeapiService::class.java)
//        val pokemonRespuestaCall = service.obtenerListaPokemon()
//
//        pokemonRespuestaCall.enqueue(object : Callback<PokemonRespuesta> {
//            override fun onResponse(
//                call: Call<PokemonRespuesta>,
//                response: Response<PokemonRespuesta>
//            ) {
//                if (response.isSuccessful){
//                    val pokemonRespuesta = response.body()
//                    listaPokemon = pokemonRespuesta?.results
//                    Log.i("tag",listaPokemon?.size.toString())
//                    bandera = true;
//
//                } else {
//                    Log.e("TAG", "onResponse "+response.errorBody())
//                    bandera= false;
//                }
//            }
//
//            override fun onFailure(call: Call<PokemonRespuesta>, t: Throwable) {
//                Log.e("TAG", "onFailure "+ t.message)
//                bandera= false;
//            }
//
//        })
//
//        return listaPokemon
//
//
//    }

    override fun obtenerDatos(){
            val retrofit = Retrofit.Builder().baseUrl("http://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(PokeapiService::class.java)
        val pokemonRespuestaCall = service.obtenerListaPokemon()

        pokemonRespuestaCall.enqueue(object : Callback<PokemonRespuesta>{
            override fun onResponse(
                call: Call<PokemonRespuesta>,
                response: Response<PokemonRespuesta>
            ) {
                if (response.isSuccessful){
                    val pokemonRespuesta = response.body()
                    val listaPokemon:ArrayList<Pokemon>? = pokemonRespuesta?.results

                    if (listaPokemon != null) {
                        view.setDatos(listaPokemon)
                        //listaPokemonAdapter.agregarPokemones(listaPokemon)
                    }
                } else {
                    Log.e("TAG", "onResponse "+response.errorBody())
                }
            }

            override fun onFailure(call: Call<PokemonRespuesta>, t: Throwable) {
                Log.e("TAG", "onFailure "+ t.message)
            }

        })
    }

    override fun getAppContext(): Context {
        return view.getAppContext()
    }
}