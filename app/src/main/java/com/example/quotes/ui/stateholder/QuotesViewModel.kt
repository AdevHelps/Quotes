package com.example.quotes.ui.stateholder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quotes.data.Quote
import com.example.quotes.data.repository.QuotesRepositoryInterface

class QuotesViewModel(private val quotesRepositoryInterface: QuotesRepositoryInterface) : ViewModel() {

    fun quoteToRepository(quote: Quote){
        quotesRepositoryInterface.quoteToDb(quote)
    }

    fun updatedQuoteToRepository(currentQuote: Quote, newQuote: Quote){
        quotesRepositoryInterface.updateQuoteInDb(currentQuote, newQuote)
    }

    suspend fun getQuotesListFromRepository(): MutableLiveData<MutableList<Quote>> {
        val quotesMutableLiveData = MutableLiveData<MutableList<Quote>>()
        quotesMutableLiveData.value = quotesRepositoryInterface.retrievedQuotesFromDb()
        return quotesMutableLiveData
    }

    fun requestQuoteDeleteToRepository(quote: Quote){
        quotesRepositoryInterface.deleteQuoteFromDb(quote)
    }
}