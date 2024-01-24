package com.example.quotes.ui.stateholder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quotes.data.repository.QuotesRepositoryInterface
import java.lang.IllegalArgumentException

class QuotesViewModelFactory(
    private val quotesRepositoryInterface: QuotesRepositoryInterface
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(QuotesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuotesViewModel(quotesRepositoryInterface) as T
        }
        throw IllegalArgumentException("View model class not found")
    }
}