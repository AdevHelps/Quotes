package com.example.quotes.ui.util

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import com.google.android.material.textfield.TextInputEditText

fun requestFocusAndShowKeyboard(
    textInputEditText: TextInputEditText,
    fragmentActivity: FragmentActivity
) {
    textInputEditText.requestFocus()
    textInputEditText.setSelection(textInputEditText.text.toString().length)
    val inputMethodManager =
        fragmentActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(textInputEditText, InputMethodManager.SHOW_IMPLICIT)
}