package com.example.myapplication.data.model

import android.util.Log

data class Pokemon(val name:String, val url:String, val id:Int) {

    fun getId2():Int{
        val urlPartes = url.split("/")
        return urlPartes[urlPartes.size-2].toInt()
    }
}