package com.example.quotes.data.repository

import com.example.quotes.data.Quote
import com.example.quotes.data.source.SqliteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuotesRepositoryImpl @Inject constructor(): QuotesRepositoryInterface {

    @Inject lateinit var sqliteDatabase: SqliteDatabase

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

    override fun getQuotesListSize(): Int {
        return sqliteDatabase.getTableSize()
    }

    override fun deleteQuoteFromDb(quote: Quote) {
        CoroutineScope(Dispatchers.IO).launch {
            sqliteDatabase.deleteQuote(quote)
        }
    }
}