package com.example.quotes.ui.uielements.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotes.ui.uielements.activitycontainer.FragmentToActivityCommunicationInterface
import com.example.quotes.data.Quote
import com.example.quotes.ui.stateholder.QuotesViewModel
import com.example.quotes.R
import com.example.quotes.data.repository.QuotesRepositoryInterface
import com.example.quotes.databinding.FragmentMainBinding
import com.example.quotes.ui.stateholder.QuotesViewModelFactory
import com.example.quotes.ui.uielements.recyclerview.RecyclerViewAdapter
import com.example.quotes.ui.uielements.recyclerview.RecyclerViewClickEventHandlingInterface
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main), RecyclerViewClickEventHandlingInterface {

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: RecyclerViewAdapter
    @Inject lateinit var fragmentToActivityCommunicationInterface: FragmentToActivityCommunicationInterface
    @Inject lateinit var quotesRepositoryInterface: QuotesRepositoryInterface
    private lateinit var quotesViewModel: QuotesViewModel
    private val args: MainFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentToActivityCommunicationInterface = context as FragmentToActivityCommunicationInterface
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.apply {

            val quotesViewModelFactory = QuotesViewModelFactory(quotesRepositoryInterface)
            quotesViewModel = ViewModelProvider(
                this@MainFragment,
                quotesViewModelFactory
            )[QuotesViewModel::class.java]

            quotesViewModel.getQuotesListSizeFromRepository()
                .observe(viewLifecycleOwner) { quotesListSize ->
                    if (quotesListSize > 0) {
                        binding.noItemsLinearLayout.visibility = View.GONE
                    }
            }

            CoroutineScope(Dispatchers.Main).launch {
                quotesViewModel.getQuotesListFromRepository()
                    .observe(viewLifecycleOwner) { quotesList ->

                        adapter = RecyclerViewAdapter(
                            quotesList,
                            this@MainFragment
                        )
                        recyclerView.adapter = adapter
                        recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    }
            }

            val isUpdated = args.isUpdated
            if (isUpdated) {
                fragmentToActivityCommunicationInterface.showSnackBar("Not modified")
            }

            addQuoteFAB.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_createQuoteFragment)
            }
        }
    }

    override fun onRvItemClick(quote: Quote) {
        val action =
            MainFragmentDirections.actionMainFragmentToUpdateQuoteFragment(quote.quote)
        findNavController().navigate(action)
    }

    override fun onRvItemLongClick(position: Int, quotesList: MutableList<Quote>) {
        quotesViewModel.requestQuoteDeleteToRepository(quotesList[position])
        quotesList.removeAt(position)
        adapter.notifyItemRemoved(position)

        if (quotesList.size == 0) {
            binding.noItemsLinearLayout.visibility = View.VISIBLE
        }
    }
}