package com.example.quotes.ui.uielements

import com.example.quotes.ui.stateholder.QuotesViewModel

interface FragmentToActivityCommunicationInterface {

    fun showSnackBar(message: String)

    fun initializeQuotesViewModel(): QuotesViewModel

}