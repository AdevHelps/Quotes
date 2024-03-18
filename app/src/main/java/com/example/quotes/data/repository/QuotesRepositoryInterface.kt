package com.example.quotes.data.repository

import com.example.quotes.data.Quote

interface QuotesRepositoryInterface {

    fun quoteToDb(quote: Quote)

    fun updateQuoteInDb(currentQuote: Quote, newQuote: Quote)

    suspend fun retrievedQuotesFromDb(): MutableList<Quote>

    fun getQuotesListSize(): Int

    fun deleteQuoteFromDb(quote: Quote)

}