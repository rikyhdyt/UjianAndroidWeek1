package com.bcaf.ujianweek1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu.*

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        btnKeluar.setOnClickListener(View.OnClickListener {
            keluar(it)
        })

        btnCheckIn.setOnClickListener(View.OnClickListener {
            login(it)
        })

        btnIzin.setOnClickListener(View.OnClickListener {
            izin(it)
        })
    }

    fun keluar(v:View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun login(v:View){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    fun izin(v:View){
        val intent = Intent(this, Izin::class.java)
        startActivity(intent)
    }


}