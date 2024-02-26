package com.example.quotes.ui.uielements.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quotes.data.Quote
import com.example.quotes.databinding.RecyclerviewRowDesignBinding

class RecyclerViewAdapter(
    private val quotesList: MutableList<Quote>,
    private val recyclerViewClickEventHandling: RecyclerViewClickEventHandlingInterface,
): RecyclerView.Adapter<RecyclerViewAdapter.QuotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        return QuotesViewHolder(
            RecyclerviewRowDesignBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return quotesList.size
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        val quote = quotesList[position]
        holder.apply {
            binding.quoteTv.text = quote.quote
            bind(quote)
        }
    }

    inner class QuotesViewHolder(
        val binding: RecyclerviewRowDesignBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(quote: Quote) {
            binding.quoteCardView.setOnClickListener {
                recyclerViewClickEventHandling.onRvItemClick(quote)
            }
        }

        init {
            binding.quoteCardView.setOnLongClickListener {
                recyclerViewClickEventHandling.onRvItemLongClick(adapterPosition, quotesList)
                true
            }
        }
    }
}