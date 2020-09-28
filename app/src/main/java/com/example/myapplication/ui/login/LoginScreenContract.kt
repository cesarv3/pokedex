package com.example.myapplication.ui.login

import android.content.Context
import android.widget.EditText

interface LoginScreenContract {
    interface View{

        fun validarCampo(editText: EditText) : Boolean

        fun getAppContext(): Context

    }

    interface Presenter{
        fun login(username: String, password: String)

        fun getAppContext(): Context



    }
}