package com.example.quotes.ui.stateholder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quotes.data.Quote
import com.example.quotes.data.repository.QuotesRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val quotesRepositoryInterface: QuotesRepositoryInterface
): ViewModel() {

    fun quoteToRepository(quote: Quote){
        quotesRepositoryInterface.quoteToDb(quote)
    }

    fun updatedQuoteToRepository(currentQuote: Quote, newQuote: Quote){
        quotesRepositoryInterface.updateQuoteInDb(currentQuote, newQuote)
    }

    suspend fun getQuotesListFromRepository(): MutableLiveData<MutableList<Quote>> {
        val quotesLiveData = MutableLiveData<MutableList<Quote>>()
        quotesLiveData.value = quotesRepositoryInterface.retrievedQuotesFromDb()
        return quotesLiveData
    }

    fun getQuotesListSizeFromRepository(): MutableLiveData<Int> {
        val quotesListSizeLiveData = MutableLiveData<Int>()
        quotesListSizeLiveData.value = quotesRepositoryInterface.getQuotesListSize()
        return quotesListSizeLiveData
    }

    fun requestQuoteDeleteToRepository(quote: Quote){
        quotesRepositoryInterface.deleteQuoteFromDb(quote)
    }
}