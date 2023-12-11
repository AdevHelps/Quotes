package com.example.quotes.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import com.example.quotes.QuotesModel
import com.example.quotes.QuotesViewModel
import com.example.quotes.Utilities
import com.example.quotes.databinding.ActivityUpdateQuoteBinding

class UpdateQuoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateQuoteBinding
    private val mainActivity = MainActivity()
    private lateinit var updatedQuote: String
    private lateinit var quotesViewModel: QuotesViewModel
    private var outdatedQuote: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUpdateQuoteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {

            updatedQuoteEditeText.requestFocus()
            updatedQuoteEditeText.setSelection(updatedQuoteEditeText.text.length)

            outdatedQuote = intent.getStringExtra("quoteSent")
            updatedQuoteEditeText.text = Editable.Factory.getInstance().newEditable(outdatedQuote)

            quotesViewModel = ViewModelProvider(
                this@UpdateQuoteActivity
            )[QuotesViewModel::class.java]

            updateQuoteFAB.setOnClickListener {
                updatedQuote = updatedQuoteEditeText.text.toString()

                if (updatedQuote.isEmpty()) {
                    Utilities.showSnackBar(updateQuoteMainLayout, "Empty field")

                }

                if (updatedQuote != outdatedQuote) {
                    val outdatedQuoteEncapsulated = QuotesModel(outdatedQuote.toString())
                    val updatedQuoteEncapsulated = QuotesModel(updatedQuote.trim())
                    quotesViewModel.updatedQuoteToRepository(
                        outdatedQuoteEncapsulated,
                        updatedQuoteEncapsulated
                    )
                    Utilities.openActivity(this@UpdateQuoteActivity, mainActivity)

                } else {
                    Utilities.openActivity(this@UpdateQuoteActivity, mainActivity)
                    Intent(this@UpdateQuoteActivity, MainActivity::class.java).also {
                        it.putExtra("isNotUpdated", true)
                        startActivity(it)
                    }
                }
            }
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (updatedQuote != outdatedQuote) {
                    Utilities.checkingToCancelQuote(
                        this@UpdateQuoteActivity,
                        this@UpdateQuoteActivity
                    )

                } else Utilities.openActivity(this@UpdateQuoteActivity, mainActivity)

            }
        })
    }
}
