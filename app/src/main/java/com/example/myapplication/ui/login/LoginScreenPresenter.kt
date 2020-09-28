package com.example.myapplication.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.myapplication.ui.main.MainScreenActivity

class LoginScreenPresenter(val view: LoginScreenContract.View) : LoginScreenContract.Presenter {


    override fun login(username: String, password: String) {

            if (username.equals("cesar") && password.equals("villa"))
            {

                Toast.makeText(getAppContext(),"Accediendo...",Toast.LENGTH_SHORT).show()
                val intent = Intent(getAppContext(),MainScreenActivity::class.java).apply {  }
                startActivity(getAppContext(),intent, Bundle.EMPTY)

            } else {
                Toast.makeText(getAppContext(),"Usuario o contrasenia invalidos.",Toast.LENGTH_SHORT).show()
            }



    }

    override fun getAppContext(): Context {

            return view.getAppContext()

    }
}