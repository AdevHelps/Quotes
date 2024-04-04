package com.example.quotes.di

import com.example.quotes.data.repository.QuotesRepositoryImpl
import com.example.quotes.data.repository.QuotesRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelInterfacesBinder {

    @Binds
    abstract fun bindQuotesRepositoryInterface(
        quotesRepositoryImpl: QuotesRepositoryImpl
    ): QuotesRepositoryInterface
}