package com.example.quotes.ui.stateholder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quotes.data.Quote
import com.example.quotes.data.repository.QuotesRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(): ViewModel() {

    @Inject lateinit var quotesRepositoryInterface: QuotesRepositoryInterface

    fun insertQuote(quote: Quote){
        quotesRepositoryInterface.insertQuote(quote)
    }

    fun updatedQuote(currentQuote: Quote, newQuote: Quote){
        quotesRepositoryInterface.updateQuote(currentQuote, newQuote)
    }

    suspend fun getQuotesList(): MutableLiveData<MutableList<Quote>> {
        val quotesLiveData = MutableLiveData<MutableList<Quote>>()
        quotesLiveData.value = quotesRepositoryInterface.retrievedQuotes()
        return quotesLiveData
    }

    fun getQuotesListSize(): MutableLiveData<Int> {
        val quotesListSizeLiveData = MutableLiveData<Int>()
        quotesListSizeLiveData.value = quotesRepositoryInterface.getQuotesListSize()
        return quotesListSizeLiveData
    }

    fun requestQuoteDelete(quote: Quote){
        quotesRepositoryInterface.deleteQuote(quote)
    }
}