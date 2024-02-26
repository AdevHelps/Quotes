package com.example.quotes.ui.uielements

import android.content.Context
import androidx.navigation.NavController
import com.example.quotes.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class Utility {

    companion object {

        fun showQuitWithoutSavingDialog(context: Context, navController: NavController) {
            MaterialAlertDialogBuilder(context, R.style.customAlertDialogTheme).also {
                it.setMessage("Quit without saving?")
                it.setPositiveButton("CANCEL") { dialog, _ ->
                    dialog.dismiss()
                }
                it.setNegativeButton("QUIT") { dialog, _ ->
                    dialog.dismiss()
                    navController.popBackStack()
                }
                it.show()
            }
        }
    }
}