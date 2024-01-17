package com.example.quotes.ui.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotes.ui.FragmentToActivityCommunicationInterface
import com.example.quotes.data.Quote
import com.example.quotes.domain.QuotesViewModel
import com.example.quotes.R
import com.example.quotes.databinding.FragmentMainBinding
import com.example.quotes.ui.recyclerview.RecyclerViewAdapter
import com.example.quotes.ui.recyclerview.RecyclerViewClickEventHandlingInterface

class MainFragment : Fragment(R.layout.fragment_main), RecyclerViewClickEventHandlingInterface {

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var fragmentToActivityCommunicationInterface: FragmentToActivityCommunicationInterface
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

            mainToolbar.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.milkyYellow))
            mainToolbar.title = ContextCompat.getString(requireContext(), R.string.app_name)

            val isUpdated = args.isUpdated
            if (isUpdated) {
                fragmentToActivityCommunicationInterface.showSnackBar("Not modified")
            }

            addQuoteFAB.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_enterQuoteFragment)
            }

            quotesViewModel = fragmentToActivityCommunicationInterface.initializeQuotesViewModel()
            quotesViewModel.getQuotesListFromRepository().observe(requireActivity()) { quotesList ->

                adapter = RecyclerViewAdapter(quotesList, this@MainFragment)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(requireContext())

                if (quotesList.size > 0) {
                    binding.noItemsLinearLayout.visibility = View.GONE
                }
            }
        }
    }

    override fun onRvItemClick(position: Int, quote: Quote) {
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