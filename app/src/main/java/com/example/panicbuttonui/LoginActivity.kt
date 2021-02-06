package com.example.panicbuttonui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.panicbuttonui.helper.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val image="https://pesonasia.com/public/uploads/posts/1612527868selamat datang.PNG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setStatusBackgroundColor(getMyColor(R.color.white))
        setLightStatusBar(true)
//        imgIllustration.loadImage(image)
        btnLogin.setOnClickListener {
            login()
        }

    }

    private fun login(){
        val phone=edtPhone.text.toString()
        val password=edtName.text.toString()
        if (isNullOrEmpty(arrayOf(phone, password))) {
            toast("Nomor Telephon dan Nama tidak boleh kosong")
            return
        }else{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}