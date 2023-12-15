package com.example.quotes.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.text.HtmlCompat
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController

class CheckingToCancelQuoteDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Quit without saving?")
                .setPositiveButton(
                    HtmlCompat
                        .fromHtml(
                            "<font color='#4285F4'>CANCEL</font>",
                            HtmlCompat.FROM_HTML_MODE_LEGACY
                        )
                ) { dialog, id ->
                    dialog.dismiss()

                }
                .setNegativeButton(
                    HtmlCompat
                        .fromHtml(
                            "<font color='#4285F4'>QUIT</font>",
                            HtmlCompat.FROM_HTML_MODE_LEGACY
                        )
                )  { dialog, id ->

                    findNavController().popBackStack()

                }

            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }

}