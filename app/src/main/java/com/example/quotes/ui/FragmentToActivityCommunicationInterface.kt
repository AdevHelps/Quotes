package com.example.quotes.ui

import com.example.quotes.domain.QuotesViewModel

interface FragmentToActivityCommunicationInterface {

    fun showSnackBar(message: String)

    fun initializeQuotesViewModel(): QuotesViewModel

}