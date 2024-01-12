package com.example.quotes.repository

import android.content.Context
import com.example.quotes.QuoteModel
import com.example.quotes.SqliteDatabase

class QuotesRepositoryImpl(context: Context): QuotesRepositoryInterface {

    private var sqliteDatabase = SqliteDatabase(context)

    override fun quoteToDb(quotesModel: QuoteModel) {
        sqliteDatabase.insertQuote(quotesModel)
    }

    override fun updateQuoteInDb(currentQuote: QuoteModel, newQuote: QuoteModel) {
        sqliteDatabase.updateQuote(currentQuote, newQuote)
    }

    override fun retrievedQuotesFromDb(): MutableList<QuoteModel> {
        return sqliteDatabase.retrieveAllQuotes()
    }

    override fun deleteQuoteFromDb(quote: QuoteModel) {
        sqliteDatabase.deleteQuote(quote)
    }
}