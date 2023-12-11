package com.example.quotes.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotes.QuotesModel
import com.example.quotes.QuotesViewModel
import com.example.quotes.Utilities
import com.example.quotes.databinding.ActivityMainBinding
import com.example.quotes.recyclerview.RecyclerViewAdapter
import com.example.quotes.recyclerview.RecyclerViewClickEventHandling
import com.example.quotes.R

class MainActivity : AppCompatActivity(), RecyclerViewClickEventHandling {

    private lateinit var binding: ActivityMainBinding
    private lateinit var quotesViewModel: QuotesViewModel
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.mainActivityVariable = this
        super.onCreate(savedInstanceState)
        binding.apply {
            setSupportActionBar(mainToolbar)

            val isNotUpdated = intent.getBooleanExtra("isNotUpdated", false)
            if (isNotUpdated) {
                Utilities.showSnackBar(mainActivityMainLayout, "Not modified")
            }

            quotesViewModel = ViewModelProvider(this@MainActivity)[QuotesViewModel::class.java]
            quotesViewModel.recyclerViewDataList()
                .observe(this@MainActivity) { quotesList ->

                    adapter = RecyclerViewAdapter(quotesList, this@MainActivity)
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

                    if (quotesList.size > 0) {
                        binding.noItemsLL.visibility = View.GONE
                    }
                }

        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }

    fun openEnterQuoteActivity() {
        val enterQuoteActivity = EnterQuoteActivity()
        Utilities.openActivity(this, enterQuoteActivity)
    }

    override fun onRvItemClick(position: Int, quote: QuotesModel) {
        Intent(this, UpdateQuoteActivity::class.java).also {
            it.putExtra("quoteSent", quote.quote)
            startActivity(it)
        }
    }

    override fun onRvItemLongClick(position: Int, quotesList: MutableList<QuotesModel>) {
        quotesViewModel.requestQuoteDeleteToRepository(quotesList[position])
        quotesList.removeAt(position)
        adapter.notifyItemRemoved(position)

        if (quotesList.size == 0) {
            binding.noItemsLL.visibility = View.VISIBLE

        }
    }

}