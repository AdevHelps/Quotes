package com.example.quotes.ui.uielements.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quotes.ui.uielements.activitycontainer.FragmentToActivityCommunicationInterface
import com.example.quotes.data.Quote
import com.example.quotes.ui.stateholder.QuotesViewModel
import com.example.quotes.R
import com.example.quotes.data.repository.QuotesRepositoryInterface
import com.example.quotes.databinding.FragmentUpdateQuoteBinding
import com.example.quotes.ui.stateholder.QuotesViewModelFactory
import com.example.quotes.ui.uielements.Utility
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UpdateQuoteFragment : Fragment(R.layout.fragment_update_quote) {

    private lateinit var binding: FragmentUpdateQuoteBinding
    @Inject lateinit var fragmentToActivityCommunicationInterface: FragmentToActivityCommunicationInterface
    @Inject lateinit var quotesRepositoryInterface: QuotesRepositoryInterface
    private val args: UpdateQuoteFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        fragmentToActivityCommunicationInterface = context as FragmentToActivityCommunicationInterface
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdateQuoteBinding.bind(view)
        binding.apply {

            val quotesViewModelFactory = QuotesViewModelFactory(quotesRepositoryInterface)
            val quotesViewModel by viewModels<QuotesViewModel> { quotesViewModelFactory }

            Utility.requestFocusAndShowKeyboard(updateQuoteTextInputFieldEditText, requireActivity())
            updateQuoteTextInputFieldEditText.setSelection(
                updateQuoteTextInputFieldEditText.text!!.length
            )

            updateQuoteTextInputFieldEditText.setText(args.quote)

            updateQuoteFAB.setOnClickListener {

                if (updateQuoteTextInputFieldEditText.text.toString().isEmpty()) {
                    fragmentToActivityCommunicationInterface.showSnackBar("Empty field")

                }

                if (updateQuoteTextInputFieldEditText.text.toString() != args.quote) {
                    val outdatedQuoteEncapsulated = Quote(args.quote)
                    val updatedQuoteEncapsulated =
                        Quote(updateQuoteTextInputFieldEditText.text.toString().trim())
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

            val callback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                    if (updateQuoteTextInputFieldEditText.text.toString() != args.quote) {
                        Utility.showQuitWithoutSavingDialog(requireContext(), findNavController())

                    } else findNavController().navigate(R.id.action_updateQuoteFragment_to_mainFragment)
                }
            }
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        }
    }
}

