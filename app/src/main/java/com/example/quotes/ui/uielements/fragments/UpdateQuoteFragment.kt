package com.example.quotes.ui.uielements.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quotes.ui.uielements.activitycontainer.FragmentToActivityCommunicationInterface
import com.example.quotes.data.Quote
import com.example.quotes.ui.stateholder.QuotesViewModel
import com.example.quotes.R
import com.example.quotes.databinding.FragmentUpdateQuoteBinding
import com.example.quotes.ui.uielements.Utility

class UpdateQuoteFragment : Fragment(R.layout.fragment_update_quote) {

    private lateinit var binding: FragmentUpdateQuoteBinding
    private lateinit var updatedQuote: Editable
    private var outdatedQuote: String = ""
    private lateinit var fragmentToActivityCommunicationInterface: FragmentToActivityCommunicationInterface
    private lateinit var quotesViewModel: QuotesViewModel
    private val args: UpdateQuoteFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        fragmentToActivityCommunicationInterface =
            context as FragmentToActivityCommunicationInterface

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                if (updatedQuote.toString() != outdatedQuote) {
                    Utility.showQuitWithoutSavingDialog(requireContext(), findNavController())

                } else findNavController().navigate(R.id.action_updateQuoteFragment_to_mainFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdateQuoteBinding.bind(view)
        binding.apply {

            updateQuoteTextInputFieldEditText.requestFocus()
            updateQuoteTextInputFieldEditText.setSelection(
                updateQuoteTextInputFieldEditText.text!!.length
            )

            quotesViewModel = fragmentToActivityCommunicationInterface.initializeQuotesViewModel()

            outdatedQuote = args.quote
            updateQuoteTextInputFieldEditText.setText(outdatedQuote)

            updatedQuote = updateQuoteTextInputFieldEditText.text!!
            updateQuoteFAB.setOnClickListener {

                if (updateQuoteTextInputFieldEditText.text.toString().isEmpty()) {
                    fragmentToActivityCommunicationInterface.showSnackBar("Empty field")

                }

                if (updatedQuote.toString() != outdatedQuote) {
                    val outdatedQuoteEncapsulated = Quote(outdatedQuote)
                    val updatedQuoteEncapsulated = Quote(updatedQuote.toString().trim())
                    quotesViewModel.updatedQuoteToRepository(
                        outdatedQuoteEncapsulated,
                        updatedQuoteEncapsulated
                    )
                    findNavController().popBackStack()

                } else {
                    val action =
                        UpdateQuoteFragmentDirections.actionUpdateQuoteFragmentToMainFragment(true)
                    findNavController().navigate(action)
                }
            }
        }
    }
}

