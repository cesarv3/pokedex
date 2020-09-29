package com.example.myapplication.ui.login

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_login_screen.*

class LoginScreenActivity : Activity(), View.OnClickListener,LoginScreenContract.View {
    private lateinit var presenter: LoginScreenContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        loginBtn.setOnClickListener(this)
        presenter = LoginScreenPresenter(this)



    }

    override fun onClick(v: View?) {
        if (validarCampo(usernameTxt) && validarCampo(passwordTxt))
        {
            presenter.login(usernameTxt.text.toString(),passwordTxt.text.toString())
        }
    }

    override fun validarCampo(editText: EditText): Boolean {
        return if (editText.text.isNullOrBlank() || editText.text.isNullOrEmpty()) {
            editText.error = "Error, no puede estar vacio"
            false
        } else {
            true
        }
    }

    override fun getAppContext(): Context {
        return this
    }


}