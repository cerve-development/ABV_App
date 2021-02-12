package com.fair.tool_belt_abv.utils

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.databinding.DialogFeedbackBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object FeedbackDialog {

    private var dialogBinding : DialogFeedbackBinding? = null
    private val dialogViewBinding get() = dialogBinding!!

    private var dialog : AlertDialog? = null


    fun Context?.showFeedbackDialog(sendFunc : (string: String) -> Unit) {

        if(this != null) {


            val layoutInflater = LayoutInflater.from(this)
            dialogBinding = DialogFeedbackBinding.inflate(layoutInflater)
            val userInput = dialogViewBinding.feedbackText.text

            if (dialog != null) {
                dismissDialog()
            }

            dialog = MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
                .setView(dialogViewBinding.root)
                .setTitle(getString(R.string.feedback_submit))
                .setPositiveButton(getString(R.string.send_)) { _, _ ->

                    if(!userInput.isNullOrEmpty()){
                        sendFunc(userInput.toString())
                    }

                }
                .setNegativeButton(getString(R.string.cancel_), null)
                .show()

        }

    }

    private fun dismissDialog() {
        Log.d("Hello", "dismissDialog()")
        dialogBinding = null
        dialog?.dismiss()
        dialog = null
    }

}