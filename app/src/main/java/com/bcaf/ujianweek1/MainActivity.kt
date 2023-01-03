package com.bcaf.ujianweek1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu.*

class MainActivity : AppCompatActivity() {
    val defaultUsername = "admin"
    val defaultPassword = "admin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener(View.OnClickListener {
            checkUsername(it)
        })

    }

    fun checkUsername(v : View){
        if (txtUsername.text.contentEquals(defaultUsername) && txtPassword.text.contentEquals(defaultPassword)){
            val intent = Intent(this, Menu::class.java)
            intent.putExtra("username", txtUsername.text.toString())
            intent.putExtra("password", txtPassword.text.toString())
            startActivity(intent)

        }else{
            Toast.makeText(applicationContext, "Username atau password salah", Toast.LENGTH_LONG).show()
        }
    }

}