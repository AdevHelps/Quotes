package com.example.quotes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quotes.QuoteModel
import com.example.quotes.repository.QuotesRepositoryInterface

class QuotesViewModel(val quotesRepositoryInterface: QuotesRepositoryInterface) : ViewModel() {

    fun quoteToRepository(quotesModel: QuoteModel){
        quotesRepositoryInterface.quoteToDb(quotesModel)
    }

    fun updatedQuoteToRepository(currentQuote: QuoteModel, newQuote: QuoteModel){
        quotesRepositoryInterface.updateQuoteInDb(currentQuote, newQuote)
    }

    fun getQuotesListFromRepository(): MutableLiveData<MutableList<QuoteModel>> {
        val quotesMutableLiveData = MutableLiveData<MutableList<QuoteModel>>()
        quotesMutableLiveData.value = quotesRepositoryInterface.retrievedQuotesFromDb()
        return quotesMutableLiveData
    }

    fun requestQuoteDeleteToRepository(quote: QuoteModel){
        quotesRepositoryInterface.deleteQuoteFromDb(quote)
    }
}