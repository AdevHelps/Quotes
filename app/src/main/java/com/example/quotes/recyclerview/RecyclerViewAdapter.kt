package com.example.quotes.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quotes.QuotesModel
import com.example.quotes.databinding.RecyclerviewRowDesignBinding

class RecyclerViewAdapter(
    private val quotesList: MutableList<QuotesModel>,
    private val recyclerViewClickEventHandling: RecyclerViewClickEventHandling,
): RecyclerView.Adapter<RecyclerViewAdapter.QuotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        return QuotesViewHolder(
            RecyclerviewRowDesignBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), quotesList, recyclerViewClickEventHandling
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

    class QuotesViewHolder(
        val binding: RecyclerviewRowDesignBinding,
        private val quotesList: MutableList<QuotesModel>,
        private val rvClickEvenHandling: RecyclerViewClickEventHandling
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(quote: QuotesModel) {
            binding.quoteCardView.setOnClickListener {
                rvClickEvenHandling.onRvItemClick(adapterPosition, quote)
            }
        }

        init {
            binding.quoteCardView.setOnLongClickListener {
                rvClickEvenHandling.onRvItemLongClick(adapterPosition, quotesList)
                true
            }
        }
    }
}