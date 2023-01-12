package com.fair.tool_belt_abv.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText

object Utility {

    fun View?.go(id: Int) {

        if (this != null) {

            try {
                findNavController().navigate(id)
            } catch (e: Exception) {
                //todo tell the user something about navigation doesn't work
            }

        }

    }

    fun Context.toast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun TextInputEditText?.isLeadingDecimal() : String? {
        return if (this != null) {

            val startsWithDecimal = this.toString().startsWith('.')
            if (startsWithDecimal) { "0${this.text}" } else { this.text.toString() }

        } else {

            null

        }
    }

    /**
     * Our application crashes when a user enters in ".<Number>" to avoid this crash,
     * we ensure that their entry has a "0" prefix if it has a leading decimal.
     */
    fun String.isLeadingDecimal(): String {
        return if (this.startsWith('.')) "0$this" else this
    }

}