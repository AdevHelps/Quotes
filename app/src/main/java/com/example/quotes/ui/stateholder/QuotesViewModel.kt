package com.example.quotes.ui.stateholder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotes.data.Quote
import com.example.quotes.data.repository.QuotesRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(): ViewModel() {

    @Inject lateinit var quotesRepositoryInterface: QuotesRepositoryInterface

    fun insertQuote(quote: Quote){
        viewModelScope.launch {
            quotesRepositoryInterface.insertQuote(quote)
        }
    }

    fun updatedQuote(currentQuote: Quote, newQuote: Quote){
        viewModelScope.launch {
            quotesRepositoryInterface.updateQuote(currentQuote, newQuote)
        }
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
        viewModelScope.launch {
            quotesRepositoryInterface.deleteQuote(quote)
        }
    }
}