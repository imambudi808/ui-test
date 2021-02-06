package com.example.panicbuttonui.repo

import android.media.MediaRecorder
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import java.io.File
import java.io.IOException
import java.lang.IllegalStateException
import java.util.*

/**
 * Created by imambudi808 on 06/02/2021.
 */
class RecordRepository {
    companion object{
        private var instance:RecordRepository?=null

        fun getInstance()=
            instance ?: synchronized(this){
                instance ?: RecordRepository().also { instance = it }
            }
    }

    private var output:String?=null
    private var mediaRecorder : MediaRecorder?=null
    private val dir: File = File(Environment.getExternalStorageDirectory().absolutePath+"/soundrecorder/")

    private var recordingTime:Long=0
    private var timer=Timer()
    private val recordingTimeString = MutableLiveData<String>()

    init {
        try {
            val recorderDirectory = File(Environment.getExternalStorageDirectory().absolutePath+"/soundrecorder/")
            recorderDirectory.mkdirs()
        }catch (e:IOException){
            e.printStackTrace()
        }

        if (dir.exists()){
            val count=dir.listFiles().size
            output = Environment.getExternalStorageDirectory().absolutePath+"/soundrecorder/recording"+count+".mp3"
        }

        mediaRecorder = MediaRecorder()
        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder?.setOutputFile(output)
    }

    fun starRecording(){
        try {
            println("Start recording!")
            mediaRecorder?.prepare()
            mediaRecorder?.start()
            starTimer()
        }catch (e:IllegalStateException){
            e.printStackTrace()
        }catch (e:IOException){
            e.printStackTrace()
        }
    }

    fun stopRecording(){
        mediaRecorder?.stop()
        mediaRecorder?.release()
        stopTimer()
        resetTimer()
        initRecorder()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun pauseRecording(){
        stopTimer()
        mediaRecorder?.pause()
    }

    fun resumeRecording(){
        timer= Timer()
        starTimer()
        mediaRecorder?.resume()
    }

    private fun initRecorder(){
        mediaRecorder= MediaRecorder()
        if (dir.exists()){
            val count=dir.listFiles().size
            output=Environment.getExternalStorageDirectory().absolutePath+"/soundrecorder/recording"+count+".mp3"
        }
        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder?.setOutputFile(output)
    }

    private fun resetTimer(){
        timer.cancel()
        recordingTime=0
        recordingTimeString.postValue("00:00")
    }

    private fun stopTimer(){
        timer.cancel()
    }

    private fun starTimer(){
        timer.scheduleAtFixedRate(object :TimerTask(){
            override fun run() {
                recordingTime += 1
                updateDisplay()
            }

        },1000,1000)
    }

    private fun updateDisplay(){
        val minute=recordingTime/(60)
        val second=recordingTime % 60
        val str = String.format("%d:%02d",minute,second)
        recordingTimeString.postValue(str)
    }

    fun getRecordingTime() = recordingTimeString
}