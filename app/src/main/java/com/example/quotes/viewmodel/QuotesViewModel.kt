package com.example.quotes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.quotes.QuotesModel
import com.example.quotes.QuotesRepository

class QuotesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = QuotesRepository(application)

    fun quoteToRepository(quotesModel: QuotesModel){
        repository.quoteToDb(quotesModel)
    }

    fun updatedQuoteToRepository(currentQuote: QuotesModel, newQuote: QuotesModel){
        repository.updateQuoteInDb(currentQuote, newQuote)
    }

    fun recyclerViewDataList(): MutableLiveData<MutableList<QuotesModel>> {
        return repository.retrievedQuotesFromDb()

    }

    fun requestQuoteDeleteToRepository(quote: QuotesModel){
        repository.deleteQuoteFromDb(quote)
    }
}