package com.example.quotes.di

import android.app.Application
import com.example.quotes.data.source.SqliteDatabase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    fun provideSQLiteDatabase(@ApplicationContext application: Application): SqliteDatabase {
        return SqliteDatabase(application)
    }
}