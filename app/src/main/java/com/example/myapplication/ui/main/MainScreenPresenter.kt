package com.example.myapplication.ui.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.myapplication.data.api.PokeapiService
import com.example.myapplication.data.model.Pokemon
import com.example.myapplication.data.model.PokemonRespuesta
import com.example.myapplication.data.model.User
import com.example.myapplication.ui.login.LoginScreenActivity
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainScreenPresenter(val view : MainScreenContract.View):MainScreenContract.Presenter {

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

    override fun logout() {
        //Codigo para guardar el objeto usuario dentro del shared preferences
        val preferences: SharedPreferences =  getAppContext().getSharedPreferences("com.example.myapplication",Context.MODE_PRIVATE)
        val preferencesEditor: SharedPreferences.Editor = preferences.edit()

        val gson: Gson = Gson()
        val user:User = User("","")

        val json = gson.toJson(user)
        preferencesEditor.putString("usuario",json)
        preferencesEditor.commit()

        Toast.makeText(getAppContext(),"Saliendo...", Toast.LENGTH_SHORT).show()
        val intent = Intent(getAppContext(),LoginScreenActivity::class.java).apply {  }
        ContextCompat.startActivity(getAppContext(), intent, Bundle.EMPTY)
        return
    }
}