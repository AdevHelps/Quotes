package com.example.quotes

import android.app.Application
import androidx.lifecycle.MutableLiveData

class QuotesRepository(application: Application) {

    private var sqliteDatabase = SqliteDatabase(application)

    fun quoteToDb(quotesModel: QuotesModel){
        sqliteDatabase.insertQuote(quotesModel)
    }

    fun updateQuoteInDb(currentQuote: QuotesModel, newQuote: QuotesModel){
        sqliteDatabase.updateQuote(currentQuote, newQuote)
    }

    fun retrievedQuotesFromDb(): MutableLiveData<MutableList<QuotesModel>> {
        return sqliteDatabase.retrieveAllQuotes()

    }

    fun deleteQuoteFromDb(quote: QuotesModel){
        sqliteDatabase.deleteQuote(quote)
    }

}