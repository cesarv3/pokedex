package com.example.myapplication.ui.splashscreen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.core.os.postDelayed
import com.example.myapplication.R
import com.example.myapplication.ui.login.LoginScreenActivity
import com.example.myapplication.ui.main.MainScreenActivity

class SplashScreenActivity : AppCompatActivity(),SplashScreenContract.View {
    private val duration:Long = 2000
    private lateinit var presenter: SplashScreenContract.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getSupportActionBar()?.hide();
        presenter = SplashScreenPresenter(this)
        setContentView(R.layout.activity_splash_screen)

        //Hacer este cambio en el presenter despues de verificar si esta la sesion del usuario
        if (presenter.isLogged())
        {
            Handler().postDelayed({
                val intent = Intent(this,MainScreenActivity::class.java).apply {  }
                startActivity(intent)
                finish()
            },duration)
        } else {
            Handler().postDelayed({
                val intent = Intent(this,LoginScreenActivity::class.java).apply {  }
                startActivity(intent)
                finish()
            },duration)
        }




    }

    override fun getAppContext(): Context {
        return this
    }


}