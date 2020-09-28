package com.example.myapplication.ui.splashscreen

 interface SplashScreenContract {
    interface View{

        fun login();
    }

    interface Presenter{
        fun refreshSession();
    }
}