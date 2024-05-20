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
        sqliteDatabase.insertQuote(quote)
    }

    override suspend fun updateQuote(currentQuote: Quote, newQuote: Quote) {
        sqliteDatabase.updateQuote(currentQuote, newQuote)
    }

    override suspend fun retrievedQuotes(): MutableList<Quote> {
        return sqliteDatabase.retrieveAllQuotes()
    }

    override fun getQuotesListSize(): Int {
        return sqliteDatabase.getTableSize()
    }

    override suspend fun deleteQuote(quote: Quote) {
        sqliteDatabase.deleteQuote(quote)
    }
}