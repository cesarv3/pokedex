package com.example.myapplication.data.api

import com.example.myapplication.data.model.PokemonRespuesta
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeapiService {

    @GET("pokemon")
    fun obtenerListaPokemon():Call<PokemonRespuesta>
}