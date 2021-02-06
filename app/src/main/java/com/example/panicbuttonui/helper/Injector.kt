package com.example.panicbuttonui.helper

import com.example.panicbuttonui.provider.RecordViewModelProvider
import com.example.panicbuttonui.repo.RecordRepository

/**
 * Created by imambudi808 on 06/02/2021.
 */

object InjectorUtils {
    fun provideRecordViewModelFactory():RecordViewModelProvider{
        val recordRepo=RecordRepository.getInstance()
        return RecordViewModelProvider(recordRepo)
    }

}