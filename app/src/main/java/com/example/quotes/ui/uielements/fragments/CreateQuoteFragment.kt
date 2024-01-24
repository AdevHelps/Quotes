package com.example.quotes.ui.uielements.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quotes.ui.uielements.FragmentToActivityCommunicationInterface
import com.example.quotes.data.Quote
import com.example.quotes.ui.stateholder.QuotesViewModel
import com.example.quotes.R
import com.example.quotes.databinding.FragmentCreateQuoteBinding
import com.example.quotes.ui.uielements.CheckingToCancelQuoteDialogFragment

class CreateQuoteFragment: Fragment(R.layout.fragment_create_quote) {

    private lateinit var binding: FragmentCreateQuoteBinding
    private lateinit var quoteEditTextField: Editable
    private lateinit var fragmentToActivityCommunicationInterface: FragmentToActivityCommunicationInterface
    private lateinit var quotesViewModel: QuotesViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        fragmentToActivityCommunicationInterface = context as FragmentToActivityCommunicationInterface

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                if (quoteEditTextField.isNotEmpty()) {

                    CheckingToCancelQuoteDialogFragment().show(
                        childFragmentManager,
                        "checkingToCancelQuoteDialogFragment"
                    )

                } else findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateQuoteBinding.bind(view)
        binding.apply {

            quoteET.requestFocus()
            quoteET.setSelection(quoteET.text.length)
            quoteEditTextField = binding.quoteET.text

            quotesViewModel = fragmentToActivityCommunicationInterface.initializeQuotesViewModel()

            insertQuoteFAB.setOnClickListener {
                val quote = binding.quoteET.text.toString()

                if (quoteEditTextField.isEmpty()) {
                    fragmentToActivityCommunicationInterface.showSnackBar("Empty field")

                } else {

                    val quoteEncapsulated = Quote(quote.trim())
                    quotesViewModel.quoteToRepository(quoteEncapsulated)
                    findNavController().navigate(R.id.action_enterQuoteFragment_to_mainFragment)
                }
            }
        }
    }
}