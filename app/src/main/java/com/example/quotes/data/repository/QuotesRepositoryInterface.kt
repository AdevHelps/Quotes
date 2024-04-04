package com.example.quotes.data.repository

import com.example.quotes.data.Quote

interface QuotesRepositoryInterface {

    fun insertQuote(quote: Quote)

    fun updateQuote(currentQuote: Quote, newQuote: Quote)

    suspend fun retrievedQuotes(): MutableList<Quote>

    fun getQuotesListSize(): Int

    fun deleteQuote(quote: Quote)

}