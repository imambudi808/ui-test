package com.example.panicbuttonui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.panicbuttonui.helper.*
import com.example.panicbuttonui.viewModel.RecordViewModel
import kotlinx.android.synthetic.main.activity_send_all_data.*

class SendAllDataActivity : AppCompatActivity() {

    private var vm:RecordViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_all_data)
        initInjector()
        setStatusBackgroundColor(getMyColor(R.color.white))
        setLightStatusBar(true)
//        checkNeededPermissions()


        imgStarRecord.setOnClickListener {
            startRecording()
//            if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
//                val permission = arrayOf(Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.READ_EXTERNAL_STORAGE)
//                ActivityCompat.requestPermissions(this,permission,0)
//            }else{
//                startRecording()
//            }
        }
        imgStopRecord.setOnClickListener {
            stopRecording()
        }

        btnSendData.setOnClickListener {
            toast("Tidak ada sambungan ke server")
        }
    }

//    private fun checkNeededPermissions() {
//        println("Requesting permission")
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
//            != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//            != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
//            != PackageManager.PERMISSION_GRANTED) {
//            println("Requesting permission")
//            ActivityCompat.requestPermissions(this,
//                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO), 0)
//        }
//    }

    private fun startRecording(){
        vm?.startRecording()
        imgStarRecord.visibility=View.GONE
        imgStopRecord.visibility=View.VISIBLE
        txtRekam.text="Stop"

    }
    private fun stopRecording(){
        vm?.stopRecording()
        imgStarRecord.visibility=View.VISIBLE
        imgStopRecord.visibility=View.GONE
        txtRekam.text="Rekam"
    }

    private fun initInjector(){
        val factoryVM = InjectorUtils.provideRecordViewModelFactory()
        vm=ViewModelProviders.of(this,factoryVM).get(RecordViewModel::class.java)
        vm?.getRecordingTime()?.observe(this, Observer {
            txtTimeRecord.text=it
        })
    }


}