package com.example.quotes.recyclerview

import com.example.quotes.QuoteModel

interface RecyclerViewClickEventHandling {

    fun onRvItemClick(position: Int, quote: QuoteModel)
    fun onRvItemLongClick(position: Int, quotesList: MutableList<QuoteModel>)

}