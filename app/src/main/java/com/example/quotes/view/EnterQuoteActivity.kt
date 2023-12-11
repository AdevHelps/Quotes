package com.example.quotes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.quotes.QuotesModel
import com.example.quotes.QuotesViewModel
import com.example.quotes.R
import com.example.quotes.Utilities
import com.example.quotes.databinding.ActivityEnterQuoteBinding

class EnterQuoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEnterQuoteBinding
    private lateinit var quotesViewModel: QuotesViewModel
    private lateinit var quoteEditTextField: Editable

    override fun onCreate(savedInstanceState: Bundle?) {
        quotesViewModel = ViewModelProvider(this)[QuotesViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_enter_quote)
        binding.lifecycleOwner = this
        binding.enterQuoteActivityVariable = this
        super.onCreate(savedInstanceState)
        binding.apply {
            setSupportActionBar(enterQuoteActivityToolbar)

            quoteET.requestFocus()
            quoteET.setSelection(quoteET.text.length)
            quoteEditTextField = binding.quoteET.text

        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (quoteEditTextField.isNotEmpty()) {
                    Utilities.checkingToCancelQuote(
                        this@EnterQuoteActivity,
                        this@EnterQuoteActivity
                    )
                }else finish()
            }
        })

    }

    fun onInsertQuoteFABClick() {
        val quote = binding.quoteET.text.toString()

        if (quoteEditTextField.isEmpty()) {
            Utilities.showSnackBar(binding.enterQuoteMainLayout, "Empty field")

        } else {
            val quoteEncapsulated = QuotesModel(quote.trim())
            quotesViewModel.quoteToRepository(quoteEncapsulated)
            val mainActivity = MainActivity()
            Utilities.openActivity(this@EnterQuoteActivity, mainActivity)
        }

    }

    override fun onStop() {
        super.onStop()
        finish()
    }

}
