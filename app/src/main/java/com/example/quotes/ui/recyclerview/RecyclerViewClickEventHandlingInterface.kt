package com.example.quotes.ui.recyclerview

import com.example.quotes.data.Quote

interface RecyclerViewClickEventHandlingInterface {

    fun onRvItemClick(position: Int, quote: Quote)
    fun onRvItemLongClick(position: Int, quotesList: MutableList<Quote>)

}