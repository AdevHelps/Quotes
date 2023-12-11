package com.example.quotes.recyclerview

import com.example.quotes.QuotesModel

interface RecyclerViewClickEventHandling {

    fun onRvItemClick(position: Int, quote: QuotesModel)
    fun onRvItemLongClick(position: Int, quotesList: MutableList<QuotesModel>)

}