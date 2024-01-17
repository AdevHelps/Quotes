package com.example.quotes.data.repository

import com.example.quotes.data.Quote

interface QuotesRepositoryInterface {

    fun quoteToDb(quote: Quote)

    fun updateQuoteInDb(currentQuote: Quote, newQuote: Quote)

    fun retrievedQuotesFromDb(): MutableList<Quote>

    fun deleteQuoteFromDb(quote: Quote)

}