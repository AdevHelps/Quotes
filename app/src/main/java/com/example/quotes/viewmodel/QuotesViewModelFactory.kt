package com.example.quotes.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class QuotesViewModelFactory(private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(QuotesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuotesViewModel(application) as T
        }
        throw IllegalArgumentException("View model class not found")
    }
}