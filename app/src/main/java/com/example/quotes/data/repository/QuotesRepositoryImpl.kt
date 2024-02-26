package com.example.quotes.data.repository

import android.content.Context
import com.example.quotes.data.Quote
import com.example.quotes.data.SqliteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuotesRepositoryImpl(context: Context): QuotesRepositoryInterface {

    private var sqliteDatabase = SqliteDatabase(context)

    override fun quoteToDb(quote: Quote) {
        CoroutineScope(Dispatchers.IO).launch {
            sqliteDatabase.insertQuote(quote)
        }
    }

    override fun updateQuoteInDb(currentQuote: Quote, newQuote: Quote) {
        CoroutineScope(Dispatchers.IO).launch {
            sqliteDatabase.updateQuote(currentQuote, newQuote)
        }
    }

    override suspend fun retrievedQuotesFromDb(): MutableList<Quote> {
        return withContext(Dispatchers.IO) {
            sqliteDatabase.retrieveAllQuotes()
        }
    }

    override fun deleteQuoteFromDb(quote: Quote) {
        CoroutineScope(Dispatchers.IO).launch {
            sqliteDatabase.deleteQuote(quote)
        }
    }
}