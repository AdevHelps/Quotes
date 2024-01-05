package com.example.quotes.view


import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quotes.FragmentToActivityCommunicationInterface
import com.example.quotes.databinding.ActivityFragmentsContainerBinding
import com.google.android.material.snackbar.Snackbar

class FragmentsContainerActivity : AppCompatActivity(), FragmentToActivityCommunicationInterface {

    private lateinit var binding: ActivityFragmentsContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentsContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {


        }
    }

    override fun showSnackBar(message: String) {
        Snackbar.make(binding.mainActivityMainLayout, message, Snackbar.LENGTH_SHORT).also {
            it.setBackgroundTint(Color.parseColor("#808080"))
        }.show()
    }

}