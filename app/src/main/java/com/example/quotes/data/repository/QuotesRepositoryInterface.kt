package com.example.quotes.domain.repository

import com.example.quotes.QuoteModel

interface QuotesRepositoryInterface {

    fun quoteToDb(quotesModel: QuoteModel)

    fun updateQuoteInDb(currentQuote: QuoteModel, newQuote: QuoteModel)

    fun retrievedQuotesFromDb(): MutableList<QuoteModel>

    fun deleteQuoteFromDb(quote: QuoteModel)

}