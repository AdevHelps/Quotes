package com.example.quotes.data.repository

import android.app.Application
import com.example.quotes.data.Quote
import com.example.quotes.data.source.SqliteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuotesRepositoryImpl @Inject constructor(
    private val application: Application
): QuotesRepositoryInterface {

    private val sqliteDatabase by lazy { SqliteDatabase(application) }

    override fun insertQuote(quote: Quote) {
        CoroutineScope(Dispatchers.IO).launch {
            sqliteDatabase.insertQuote(quote)
        }
    }

    override fun updateQuote(currentQuote: Quote, newQuote: Quote) {
        CoroutineScope(Dispatchers.IO).launch {
            sqliteDatabase.updateQuote(currentQuote, newQuote)
        }
    }

    override suspend fun retrievedQuotes(): MutableList<Quote> {
        return withContext(Dispatchers.IO) {
            sqliteDatabase.retrieveAllQuotes()
        }
    }

    override fun getQuotesListSize(): Int {
        return sqliteDatabase.getTableSize()
    }

    override fun deleteQuote(quote: Quote) {
        CoroutineScope(Dispatchers.IO).launch {
            sqliteDatabase.deleteQuote(quote)
        }
    }
}