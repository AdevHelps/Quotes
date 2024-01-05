package com.example.quotes.view

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quotes.FragmentToActivityCommunicationInterface
import com.example.quotes.QuotesModel
import com.example.quotes.viewmodel.QuotesViewModel
import com.example.quotes.R
import com.example.quotes.databinding.FragmentUpdateQuoteBinding
import com.example.quotes.viewmodel.QuotesViewModelFactory

class UpdateQuoteFragment : Fragment(R.layout.fragment_update_quote) {

    private lateinit var binding: FragmentUpdateQuoteBinding
    private lateinit var updatedQuote: Editable
    private lateinit var quotesViewModel: QuotesViewModel
    private var outdatedQuote: String = ""
    private var fragmentToActivityCommunicationInterface: FragmentToActivityCommunicationInterface? =
        null
    private val args: UpdateQuoteFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdateQuoteBinding.bind(view)
        binding.apply {

            updatedQuoteEditeText.requestFocus()
            updatedQuoteEditeText.setSelection(updatedQuoteEditeText.text.length)

            outdatedQuote = args.quote
            updatedQuoteEditeText.text = Editable.Factory.getInstance().newEditable(outdatedQuote)

            val application = Application()
            val quotesViewModelFactory = QuotesViewModelFactory(application)
            quotesViewModel = ViewModelProvider(
                this@UpdateQuoteFragment,
                quotesViewModelFactory,
            )[QuotesViewModel::class.java]

            updatedQuote = updatedQuoteEditeText.text
            updateQuoteFAB.setOnClickListener {

                if (updatedQuoteEditeText.text.toString().isEmpty()) {
                    fragmentToActivityCommunicationInterface?.showSnackBar("Empty field")

                }

                if (updatedQuote.toString() != outdatedQuote) {
                    val outdatedQuoteEncapsulated = QuotesModel(outdatedQuote)
                    val updatedQuoteEncapsulated = QuotesModel(updatedQuote.toString().trim())
                    quotesViewModel.updatedQuoteToRepository(
                        outdatedQuoteEncapsulated,
                        updatedQuoteEncapsulated
                    )
                    findNavController().navigate(R.id.action_updateQuoteFragment_to_mainFragment)

                } else {
                    val action =
                        UpdateQuoteFragmentDirections.actionUpdateQuoteFragmentToMainFragment(true)
                    findNavController().navigate(action)
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        fragmentToActivityCommunicationInterface =
            context as FragmentToActivityCommunicationInterface

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                if (updatedQuote.toString() != outdatedQuote) {
                    CheckingToCancelQuoteDialogFragment().show(
                        childFragmentManager,
                        "checkingToCancelQuoteDialogFragment"
                    )

                } else findNavController().navigate(R.id.action_updateQuoteFragment_to_mainFragment)

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)


    }

    override fun onDetach() {
        super.onDetach()
        fragmentToActivityCommunicationInterface = null
    }

}

