package com.example.myapplication.ui.splashscreen

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.myapplication.data.model.User
import com.google.gson.Gson

class SplashScreenPresenter(val view: SplashScreenContract.View):SplashScreenContract.Presenter {


    override fun isLogged() : Boolean {
        val gson:Gson = Gson()
        val preferences: SharedPreferences =  getAppContext().getSharedPreferences("com.example.myapplication",Context.MODE_PRIVATE)

        val json = preferences.getString("usuario","")
        if (json != null) {
            Log.i("impresion",json)
        }

        return if (json != "") {
            val user: User = gson.fromJson(json,User::class.java)
            user.username != ""
        } else false
    }

    override fun getAppContext() : Context{
        return view.getAppContext()
    }
}