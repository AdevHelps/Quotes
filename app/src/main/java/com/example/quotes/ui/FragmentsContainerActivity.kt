package com.example.quotes.ui


import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.quotes.data.repository.QuotesRepositoryImpl
import com.example.quotes.data.repository.QuotesRepositoryInterface
import com.example.quotes.databinding.ActivityFragmentsContainerBinding
import com.example.quotes.domain.QuotesViewModel
import com.example.quotes.domain.QuotesViewModelFactory
import com.google.android.material.snackbar.Snackbar

class FragmentsContainerActivity : AppCompatActivity(), FragmentToActivityCommunicationInterface {

    private lateinit var binding: ActivityFragmentsContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentsContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun showSnackBar(message: String) {
        Snackbar.make(binding.mainActivityMainLayout, message, Snackbar.LENGTH_SHORT).also {
            it.setBackgroundTint(Color.parseColor("#808080"))
        }.show()
    }

    override fun initializeQuotesViewModel(): QuotesViewModel {
        val applicationContext = applicationContext
        val quotesRepositoryInterface: QuotesRepositoryInterface = QuotesRepositoryImpl(applicationContext)
        val quotesViewModelFactory = QuotesViewModelFactory(quotesRepositoryInterface)

        return ViewModelProvider(
            this@FragmentsContainerActivity,
            quotesViewModelFactory
        )[QuotesViewModel::class.java]
    }
}