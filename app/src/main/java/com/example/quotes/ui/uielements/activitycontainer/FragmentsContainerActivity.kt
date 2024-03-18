package com.example.quotes.ui.uielements.activitycontainer


import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quotes.databinding.ActivityFragmentsContainerBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentsContainerActivity @Inject constructor(
): AppCompatActivity(), FragmentToActivityCommunicationInterface {

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
}