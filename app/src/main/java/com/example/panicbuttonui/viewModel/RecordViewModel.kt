package com.example.panicbuttonui.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.panicbuttonui.helper.RecorderState
import com.example.panicbuttonui.repo.RecordRepository

/**
 * Created by imambudi808 on 06/02/2021.
 */
class RecordViewModel(val recordRepo:RecordRepository):ViewModel() {
    var recordState:RecorderState=RecorderState.Stopped

    fun startRecording()=recordRepo.starRecording()
    fun stopRecording()=recordRepo.stopRecording()
    @RequiresApi(Build.VERSION_CODES.N)
    fun pauseRecording()=recordRepo.pauseRecording()
    @RequiresApi(Build.VERSION_CODES.N)
    fun resumeRecording()=recordRepo.resumeRecording()
    fun getRecordingTime()=recordRepo.getRecordingTime()
}