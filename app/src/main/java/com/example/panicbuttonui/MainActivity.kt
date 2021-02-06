package com.example.panicbuttonui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.panicbuttonui.helper.getMyColor
import com.example.panicbuttonui.helper.setLightStatusBar
import com.example.panicbuttonui.helper.setStatusBackgroundColor
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val image="https://pesonasia.com/public/uploads/posts/1612566675icon.JPG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setStatusBackgroundColor(getMyColor(R.color.white))
        setLightStatusBar(true)
        imgMap.setOnClickListener {
            startActivity(Intent(this, CurrentLocationActivity::class.java))
        }
        btnCallCenter.setOnClickListener {
            dial()
        }
    }

    private fun dial(){
        val nomor = "119"
        val panggil = Intent(Intent.ACTION_DIAL)
        panggil.data = Uri.fromParts("tel", nomor, null)
        startActivity(panggil)
    }


}