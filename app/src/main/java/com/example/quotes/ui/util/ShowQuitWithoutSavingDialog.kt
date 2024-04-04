package com.example.quotes.ui.util

import android.content.Context
import androidx.navigation.NavController
import com.example.quotes.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun showQuitWithoutSavingDialog(context: Context, navController: NavController) {
    MaterialAlertDialogBuilder(context, R.style.customAlertDialogTheme).also {
        it.setMessage("Quit without saving?")
        it.setPositiveButton("CANCEL") { _, _ -> }
        it.setNegativeButton("QUIT") { _, _ ->
            navController.popBackStack()
        }
        it.show()
    }
}