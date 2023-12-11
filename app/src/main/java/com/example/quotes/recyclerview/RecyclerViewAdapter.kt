package com.example.quotes.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quotes.QuotesModel
import com.example.quotes.R

class RecyclerViewAdapter(private val quotesList: MutableList<QuotesModel>,
                          private val recyclerViewClickEventHandling: RecyclerViewClickEventHandling
) :
    RecyclerView.Adapter<RecyclerViewAdapter.QuotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recyclerview_row_design, parent, false
        )

        return QuotesViewHolder(view, quotesList, recyclerViewClickEventHandling)
    }

    override fun getItemCount(): Int {
        return quotesList.size
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        val quote = quotesList[position]
        holder.quoteTv.text = quote.quote
        holder.bind(quote)

    }

    class QuotesViewHolder(itemView: View, quotesList: MutableList<QuotesModel>,
                           private val rvClickEvenHandling: RecyclerViewClickEventHandling
    ) : RecyclerView.ViewHolder(itemView) {
        val quoteTv: TextView = itemView.findViewById(R.id.quoteTv)

        fun bind(quote: QuotesModel) {
            itemView.setOnClickListener {
                rvClickEvenHandling.onRvItemClick(adapterPosition, quote)

            }
        }

        init {
            itemView.setOnLongClickListener {
                rvClickEvenHandling.onRvItemLongClick(adapterPosition, quotesList)
                true
            }
        }
    }
}