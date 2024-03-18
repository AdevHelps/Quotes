package com.example.quotes.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteDatabase(
    context: Context
): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        private const val DATABASE_NAME = "QuotesDatabase"
        private const val DATABASE_VERSION = 1

        private const val QUOTES_TABLE = "QuotesTable"
        private const val ID_COLUMN = "QuoteId"
        private const val QUOTES_COLUMN = "Quote"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val quotesQuery =
            "CREATE TABLE IF NOT EXISTS $QUOTES_TABLE ($ID_COLUMN INTEGER PRIMARY KEY AUTOINCREMENT" +
                    ", $QUOTES_COLUMN TEXT )"
        db?.execSQL(quotesQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //TODO
    }

    fun insertQuote(quote: Quote) {
        val sqliteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(QUOTES_COLUMN, quote.quote)
        sqliteDatabase.insert(QUOTES_TABLE, null, contentValues)
    }

    fun updateQuote(currentQuote: Quote, newQuote: Quote) {
        val sqliteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(QUOTES_COLUMN, newQuote.quote)
        sqliteDatabase.update(QUOTES_TABLE, contentValues, "Quote=?", arrayOf(currentQuote.quote))
    }

    fun retrieveAllQuotes(): MutableList<Quote> {
        val sqliteDatabase = this.readableDatabase
        val cursor = sqliteDatabase.rawQuery("SELECT * FROM $QUOTES_TABLE", null)
        val quotesList = mutableListOf<Quote>()
        while (cursor.moveToNext()) {
            val retrievedQuote = cursor.getString(cursor.getColumnIndexOrThrow(QUOTES_COLUMN))

            val quote = Quote(retrievedQuote)
            quotesList.add(quote)

        }
        cursor.close()
        return quotesList
    }

    fun deleteQuote(quote: Quote) {
        val sqliteDatabase = this.writableDatabase
        sqliteDatabase.delete(QUOTES_TABLE, "Quote=?", arrayOf(quote.quote))
    }
}