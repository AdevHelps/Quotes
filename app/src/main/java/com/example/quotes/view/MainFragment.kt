package com.example.quotes.view


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotes.FragmentToActivityCommunicationInterface
import com.example.quotes.QuotesModel
import com.example.quotes.QuotesViewModel
import com.example.quotes.R
import com.example.quotes.databinding.FragmentMainBinding
import com.example.quotes.recyclerview.RecyclerViewAdapter
import com.example.quotes.recyclerview.RecyclerViewClickEventHandling

class MainFragment : Fragment(R.layout.fragment_main), RecyclerViewClickEventHandling {

    private lateinit var binding: FragmentMainBinding
    private lateinit var quotesViewModel: QuotesViewModel
    private lateinit var adapter: RecyclerViewAdapter
    private var fragmentToActivityCommunicationInterface: FragmentToActivityCommunicationInterface? =
        null
    private val args: MainFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.apply {

            val isUpdated = args.isUpdated
            if (isUpdated) {
                fragmentToActivityCommunicationInterface?.showSnackBar("Not modified")

            }

            addQuoteFAB.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_enterQuoteFragment)
            }

            quotesViewModel = ViewModelProvider(this@MainFragment)[QuotesViewModel::class.java]
            quotesViewModel.recyclerViewDataList().observe(requireActivity()) { quotesList ->

                adapter = RecyclerViewAdapter(quotesList, this@MainFragment)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(requireContext())

                if (quotesList.size > 0) {
                    binding.noItemsLinearLayout.visibility = View.GONE
                }
            }
        }

    }

    override fun onRvItemClick(position: Int, quote: QuotesModel) {
        val action =
            MainFragmentDirections.actionMainFragmentToUpdateQuoteFragment(quote.quote)
        findNavController().navigate(action)

    }

    override fun onRvItemLongClick(position: Int, quotesList: MutableList<QuotesModel>) {
        quotesViewModel.requestQuoteDeleteToRepository(quotesList[position])
        quotesList.removeAt(position)
        adapter.notifyItemRemoved(position)

        if (quotesList.size == 0) {
            binding.noItemsLinearLayout.visibility = View.VISIBLE

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentToActivityCommunicationInterface = context as FragmentToActivityCommunicationInterface

    }

    override fun onDetach() {
        super.onDetach()
        fragmentToActivityCommunicationInterface = null
    }

}