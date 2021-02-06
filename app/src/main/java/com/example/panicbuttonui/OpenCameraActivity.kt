package com.example.panicbuttonui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.panicbuttonui.helper.*
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import kotlinx.android.synthetic.main.activity_open_camera.*
import java.io.File

class OpenCameraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_camera)
        setStatusBackgroundColor(getMyColor(R.color.white))
        setLightStatusBar(true)
        openCamera()
        setupButtonClick()
    }

    private fun setupButtonClick(){
        btnGoToRecord.setOnClickListener {
            startActivity(Intent(this,SendAllDataActivity::class.java))
        }
        cameraAction.setOnClickListener {
            onCaptureImage()
        }

//        cameraAction.onKeyLongPress()
    }

    private fun requestRuntimePermission() {
        Dexter.withContext(this)
            .withPermissions(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(multiplePermissionsListener)
            .check()

    }

    private val multiplePermissionsListener = object : ShortenMultiplePermissionListener() {
        override fun onPermissionsChecked(report: MultiplePermissionsReport) {
            if (report.areAllPermissionsGranted()) {
                onPermissionGrant()
            } else {
                onPermissionDenied()
            }
        }
    }

    private fun onPermissionGrant() {
        openCamera()
    }

    private fun onPermissionDenied() {
        toast("belum menerima permission")
//        finish()
    }

    private fun openCamera(){
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestRuntimePermission()
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        cameraAction.bindToLifecycle(this)
        cameraAction.isPinchToZoomEnabled=true
    }

    private fun onCaptureImage(){
        val file= File(filesDir.absoluteFile,"temp.jpg")
        val outputFileOptions = ImageCapture.OutputFileOptions.Builder(file).build()
        cameraAction.takePicture(outputFileOptions,ContextCompat.getMainExecutor(this),imgaeSavedCallback)
    }

    private val imgaeSavedCallback = object :ImageCapture.OnImageSavedCallback{
        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
            toast("berhasil mengambil gambar")
        }

        override fun onError(exception: ImageCaptureException) {
            toast("gagal mengambil gambar")
        }

    }
}