package com.example.quotes.data.repository

import android.content.Context
import com.example.quotes.data.Quote
import com.example.quotes.data.SqliteDatabase

class QuotesRepositoryImpl(context: Context): QuotesRepositoryInterface {

    private var sqliteDatabase = SqliteDatabase(context)

    override fun quoteToDb(quote: Quote) {
        sqliteDatabase.insertQuote(quote)
    }

    override fun updateQuoteInDb(currentQuote: Quote, newQuote: Quote) {
        sqliteDatabase.updateQuote(currentQuote, newQuote)
    }

    override fun retrievedQuotesFromDb(): MutableList<Quote> {
        return sqliteDatabase.retrieveAllQuotes()
    }

    override fun deleteQuoteFromDb(quote: Quote) {
        sqliteDatabase.deleteQuote(quote)
    }
}