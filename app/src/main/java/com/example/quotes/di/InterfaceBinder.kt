package com.example.quotes.di

import com.example.quotes.data.repository.QuotesRepositoryImpl
import com.example.quotes.data.repository.QuotesRepositoryInterface
import com.example.quotes.ui.uielements.activitycontainer.FragmentToActivityCommunicationInterface
import com.example.quotes.ui.uielements.activitycontainer.FragmentsContainerActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class InterfaceBinder {

    @Binds
    abstract fun bindQuotesRepositoryInterface(
        quotesRepositoryImpl: QuotesRepositoryImpl
    ): QuotesRepositoryInterface

    @Binds
    abstract fun bindFragmentToActivityCommunicationInterface(
        fragmentsContainerActivity: FragmentsContainerActivity
    ): FragmentToActivityCommunicationInterface
}