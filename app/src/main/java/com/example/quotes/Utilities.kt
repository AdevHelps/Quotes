package com.example.quotes

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.HtmlCompat
import com.google.android.material.snackbar.Snackbar

class Utilities {

    companion object {

        fun checkingToCancelQuote(context: Context, activity: Activity) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Quit without saving?")
                .setPositiveButton(
                    HtmlCompat.fromHtml
                        (
                        "<font color='#313133'>CANCEL</font>",
                        HtmlCompat.FROM_HTML_MODE_LEGACY
                    )
                ) { dialog, _ ->
                    dialog.dismiss()
                }
                .setNegativeButton(
                    HtmlCompat.fromHtml
                        (
                        "<font color='#313133'>QUIT</font>",
                        HtmlCompat.FROM_HTML_MODE_LEGACY
                    )
                ) { _, _ ->
                    activity.finish()
                }

            val dialog: AlertDialog = builder.create()
            dialog.show()

        }

        fun openActivity(context: Context, activity: Activity){
            Intent(context, activity::class.java).also { context.startActivity(it) }
        }

        fun showSnackBar(layoutContainer: ConstraintLayout, text: String){
            Snackbar.make(
                layoutContainer, text, Snackbar.LENGTH_SHORT
            ).also {
                it.setBackgroundTint(Color.parseColor("#808080"))
            }.show()
        }
    }

}