package com.example.quotes.ui.uielements.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.quotes.ui.uielements.activitycontainer.FragmentToActivityCommunicationInterface
import com.example.quotes.data.Quote
import com.example.quotes.ui.stateholder.QuotesViewModel
import com.example.quotes.R
import com.example.quotes.data.repository.QuotesRepositoryInterface
import com.example.quotes.databinding.FragmentCreateQuoteBinding
import com.example.quotes.ui.stateholder.QuotesViewModelFactory
import com.example.quotes.ui.uielements.Utility
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreateQuoteFragment: Fragment(R.layout.fragment_create_quote) {

    private lateinit var binding: FragmentCreateQuoteBinding
    @Inject lateinit var fragmentToActivityCommunicationInterface: FragmentToActivityCommunicationInterface
    @Inject lateinit var quotesRepositoryInterface: QuotesRepositoryInterface

    override fun onAttach(context: Context) {
        super.onAttach(context)

        fragmentToActivityCommunicationInterface = context as FragmentToActivityCommunicationInterface
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateQuoteBinding.bind(view)
        binding.apply {

            val quotesViewModelFactory = QuotesViewModelFactory(quotesRepositoryInterface)
            val quotesViewModel by viewModels<QuotesViewModel> { quotesViewModelFactory }

            Utility.requestFocusAndShowKeyboard(createQuoteTextInputEditText, requireActivity())
            createQuoteTextInputEditText.setSelection(createQuoteTextInputEditText.text!!.length)

            insertQuoteFAB.setOnClickListener {
                val quote = binding.createQuoteTextInputEditText.text.toString()

                if (createQuoteTextInputEditText.text.isNullOrEmpty()) {
                    fragmentToActivityCommunicationInterface.showSnackBar("Empty field")

                } else {
                    val quoteEncapsulated = Quote(quote.trim())
                    quotesViewModel.quoteToRepository(quoteEncapsulated)
                    findNavController().popBackStack()
                }
            }

            val callback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                    if (createQuoteTextInputEditText.text.toString().isNotEmpty()) {
                        Utility.showQuitWithoutSavingDialog(requireContext(), findNavController())

                    } else findNavController().navigate(R.id.action_createQuoteFragment_to_mainFragment)
                }
            }
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        }
    }
}