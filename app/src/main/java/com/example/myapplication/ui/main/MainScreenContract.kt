package com.example.myapplication.ui.main

import android.content.Context
import com.example.myapplication.data.model.Pokemon

interface MainScreenContract {

    interface View{
        fun getAppContext(): Context
        fun setDatos(listaPokemon:MutableList<Pokemon>?)

    }

    interface Presenter{
        fun obtenerDatos()
        fun getAppContext(): Context
    }
}