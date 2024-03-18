package com.example.quotes.ui.uielements

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.example.quotes.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class Utility {

    companion object {

        fun requestFocusAndShowKeyboard(
            textInputEditText: TextInputEditText,
            fragmentActivity: FragmentActivity
        ) {
            textInputEditText.requestFocus()
            val inputMethodManager =
                fragmentActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(textInputEditText, InputMethodManager.SHOW_IMPLICIT)
        }

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