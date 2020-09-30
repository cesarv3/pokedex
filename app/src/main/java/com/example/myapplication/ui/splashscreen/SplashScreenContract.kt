package com.example.myapplication.ui.splashscreen

import android.content.Context

interface SplashScreenContract {
    interface View{
        fun getAppContext():Context
    }

    interface Presenter{
        fun isLogged():Boolean
        fun getAppContext():Context
    }
}