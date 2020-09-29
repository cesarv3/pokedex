package com.example.myapplication.ui.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.api.PokeapiService
import com.example.myapplication.data.model.Pokemon
import com.example.myapplication.data.model.PokemonRespuesta
import com.example.myapplication.ui.login.LoginScreenContract
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainScreenActivity : AppCompatActivity(), MainScreenContract.View {
    private lateinit var retrofit:Retrofit
    private var offset:Int = 0;
    private lateinit var recyclerView : RecyclerView
    private lateinit var listaPokemonAdapter: ListaPokemonAdapter
    private lateinit var presenter: MainScreenContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainScreenPresenter(this)
        recyclerView = findViewById(R.id.recyclerview)
        listaPokemonAdapter = ListaPokemonAdapter(this)
        recyclerView.adapter = listaPokemonAdapter
        recyclerView.setHasFixedSize(true)
        //val layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        val layoutManager = GridLayoutManager(this,3)
        recyclerView.layoutManager = layoutManager

        presenter.obtenerDatos()


//        retrofit = Retrofit.Builder().baseUrl("http://pokeapi.co/api/v2/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()

        //funcion del presenter
        //presenter.obtenerDatos()?.let { listaPokemonAdapter.agregarPokemones(it) }
        //Log.i("dentro de activity", presenter.obtenerDatos()?.size.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.mimenu,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId)
        {
            R.id.logout -> {
                presenter.logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun getAppContext(): Context {
        return this
    }

    override fun setDatos(listaPokemon: MutableList<Pokemon>?) {
        listaPokemon?.let { listaPokemonAdapter.agregarPokemones(it) }
    }


//    fun obtenerDatos(){
//        val service = retrofit.create(PokeapiService::class.java)
//        val pokemonRespuestaCall = service.obtenerListaPokemon()
//
//        pokemonRespuestaCall.enqueue(object : Callback<PokemonRespuesta>{
//            override fun onResponse(
//                call: Call<PokemonRespuesta>,
//                response: Response<PokemonRespuesta>
//            ) {
//                if (response.isSuccessful){
//                    val pokemonRespuesta = response.body()
//                    val listaPokemon:ArrayList<Pokemon>? = pokemonRespuesta?.results
//
//                    if (listaPokemon != null) {
//                        listaPokemonAdapter.agregarPokemones(listaPokemon)
//                    }
//                } else {
//                    Log.e("TAG", "onResponse "+response.errorBody())
//                }
//            }
//
//            override fun onFailure(call: Call<PokemonRespuesta>, t: Throwable) {
//                Log.e("TAG", "onFailure "+ t.message)
//            }
//
//        })
//    }


}