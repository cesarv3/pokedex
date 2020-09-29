package com.example.myapplication.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.myapplication.data.model.User
import com.example.myapplication.ui.main.MainScreenActivity
import com.github.cliftonlabs.json_simple.JsonArray
import com.github.cliftonlabs.json_simple.JsonObject
import com.github.cliftonlabs.json_simple.Jsoner
import com.github.dozermapper.core.DozerBeanMapperBuilder
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.StringBuilder
import java.util.stream.Collectors

class LoginScreenPresenter(val view: LoginScreenContract.View) : LoginScreenContract.Presenter {


    override fun login(username: String, password: String) {
        val file:File = File(getAppContext().filesDir,"datosDB")
        val fileReader = FileReader(file)
        val bufferedReader = BufferedReader(fileReader)

        var misObjetos:JsonArray = Jsoner.deserializeMany(bufferedReader) as JsonArray
        //Log.i("objetos",misObjetos.toJson())

        var mapper = DozerBeanMapperBuilder.buildDefault()

        val jsonArray:JsonArray = misObjetos[0] as JsonArray
//        Log.i("objetos",jsonArray.toJson())
        var users:MutableList<User> = ArrayList<User>()

        if (jsonArray != null)
        {
            for (usuario in jsonArray)
            {
                users.add(User((usuario as JsonObject).get("username") as String,(usuario as JsonObject).get("password") as String))
            }
        }

//        val usuarios:List<User> = jsonArray.stream()
//            .map { obj -> mapper.map(obj, User::class.java) }
//            .collect(Collectors.toList()) as List<User>
//        Log.i("objetos",usuarios.toString())


        for (user in users)
        {
            if (username.equals(user.username) && password.equals(user.password))
            {

                Toast.makeText(getAppContext(),"Accediendo...",Toast.LENGTH_SHORT).show()
                val intent = Intent(getAppContext(),MainScreenActivity::class.java).apply {  }
                startActivity(getAppContext(),intent, Bundle.EMPTY)
                return

            }
        }
        Toast.makeText(getAppContext(),"Usuario o contrasenia invalidos.",Toast.LENGTH_SHORT).show()

        bufferedReader.close()





    }

    override fun getAppContext(): Context {

            return view.getAppContext()

    }
}