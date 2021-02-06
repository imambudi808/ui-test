package com.example.panicbuttonui.provider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.panicbuttonui.repo.RecordRepository
import com.example.panicbuttonui.viewModel.RecordViewModel

/**
 * Created by imambudi808 on 06/02/2021.
 */
class RecordViewModelProvider(val recordRepository: RecordRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecordViewModel(recordRepository) as T
    }
}