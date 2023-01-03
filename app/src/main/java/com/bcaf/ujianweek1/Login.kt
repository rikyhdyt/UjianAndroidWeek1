package com.bcaf.ujianweek1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    companion object {
        private val REQUEST_CODE_PERMISSION=999
        private val CAMERA_REQUEST_CAPTURE=1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnTakeSelfie.setOnClickListener(View.OnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.CAMERA)==PackageManager.PERMISSION_DENIED){

                    val permissions = arrayOf(Manifest.permission.CAMERA)
                    requestPermissions(permissions, REQUEST_CODE_PERMISSION)
                }
                else{
                    captureCamera()
                }
            }
        })
    }

    fun captureCamera(){
        val takeCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takeCamera, CAMERA_REQUEST_CAPTURE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            REQUEST_CODE_PERMISSION -> {
                if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    captureCamera();
                }else{
                    Toast.makeText(this, "Sorry permission denied", Toast.LENGTH_LONG).show();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode : Int, data:Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode== CAMERA_REQUEST_CAPTURE && resultCode == RESULT_OK){
            val bitmapImage = data?.extras?.get("data") as Bitmap
            imgResult.setImageBitmap(bitmapImage)
            Toast.makeText(this, "Login Foto selfie berhasil diambil", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Login foto selfie GAGAL !!!", Toast.LENGTH_LONG).show();
        }
    }
}