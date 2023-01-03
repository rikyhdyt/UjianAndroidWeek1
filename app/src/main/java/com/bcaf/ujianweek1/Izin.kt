package com.bcaf.ujianweek1

import android.Manifest
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_izin.*
import kotlinx.android.synthetic.main.activity_login.*
import java.text.SimpleDateFormat
import java.util.*

class Izin : AppCompatActivity() {

    companion object {
        private val REQUEST_CODE_PERMISSION=999
        private val CAMERA_REQUEST_CAPTURE=1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_izin)

        val spinner:Spinner = findViewById(R.id.spinnerKatergori)
        ArrayAdapter.createFromResource(this,
            R.array.izin_array,
            android.R.layout.simple_spinner_item).also {
                adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        btnKirim.setOnClickListener(View.OnClickListener {
            kirim(it)
        })

        btnDari.setOnClickListener(View.OnClickListener {
            pickDate(it)
        })

        btnSampai.setOnClickListener(View.OnClickListener {
            pickDate2(it)
        })

        btnLampiran.setOnClickListener(View.OnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.CAMERA)==PackageManager.PERMISSION_DENIED){

                    val permissions = arrayOf(Manifest.permission.CAMERA)
                    requestPermissions(permissions, Izin.REQUEST_CODE_PERMISSION)
                }
                else{
                    captureCamera()
                }
            }
        })

    }

    fun pickDate(view : View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int
            ) {
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, monthOfYear)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MMM/yyy"
                val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)
                txtDari.setText(sdf.format(c.getTime()))
            }
        }
        DatePickerDialog(this,
            dateSetListener,
            c.get(Calendar.YEAR),
            c.get(Calendar.MONTH),
            c.get(Calendar.DAY_OF_MONTH)).show()
    }

    fun pickDate2(view : View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int
            ) {
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, monthOfYear)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MMM/yyy"
                val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)
                txtSampai.setText(sdf.format(c.getTime()))
            }
        }
        DatePickerDialog(this,
            dateSetListener,
            c.get(Calendar.YEAR),
            c.get(Calendar.MONTH),
            c.get(Calendar.DAY_OF_MONTH)).show()
    }

    fun captureCamera(){
        val takeCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takeCamera, Izin.CAMERA_REQUEST_CAPTURE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            Izin.REQUEST_CODE_PERMISSION -> {
                if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    captureCamera();
                }else{
                    Toast.makeText(this, "Sorry permission denied", Toast.LENGTH_LONG).show();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode : Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== Izin.CAMERA_REQUEST_CAPTURE && resultCode == RESULT_OK){
            val bitmapImage = data?.extras?.get("data") as Bitmap
            btnLampiran.setImageBitmap(bitmapImage)
            Toast.makeText(this, "Lampiran berhasil di foto", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Foto GAGAL !!!", Toast.LENGTH_LONG).show();
        }
    }

    fun kirim(v:View){
        val intent = Intent(this, Menu::class.java)
        Toast.makeText(this, "Permintaan izin telah berhasil diinput", Toast.LENGTH_LONG).show();
        startActivity(intent)
    }
}