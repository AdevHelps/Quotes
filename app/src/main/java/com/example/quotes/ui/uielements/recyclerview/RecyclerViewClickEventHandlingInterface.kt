package com.example.quotes.ui.uielements.recyclerview

import com.example.quotes.data.Quote

interface RecyclerViewClickEventHandlingInterface {

    fun onRvItemClick(quote: Quote)
    fun onRvItemLongClick(position: Int, quotesList: MutableList<Quote>)

}