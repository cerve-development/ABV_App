package com.fair.tool_belt_abv.data.database


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.utils.toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class FirebaseRealtime(private var context: Context, private var inflating: LayoutInflater) {


    private fun sendFeedback(feedback: String?){

        val database = FirebaseDatabase.getInstance().reference

            database.child("Feedback").child(UUID.randomUUID().toString()).setValue(feedback)
                .addOnSuccessListener {
                    context.toast("Thank you for your feedback!")
                }
                .addOnFailureListener {
                    context.toast("uh oh, please connect to the internet and try again")
                }


    }

    @SuppressLint("InflateParams")
    fun feedbackDialog(){
        return context.let {

            val builder = MaterialAlertDialogBuilder(it)

            builder.apply {

                setTitle("Would you like to request a feature?\n")
                val customLayout = inflating.inflate(R.layout.dialog_feedback, null)
                setView(customLayout)
                setPositiveButton("Submit"){_,_->
                    val userInput = customLayout.findViewById<TextInputEditText>(R.id.feedbackText)

                    if(userInput.text.toString().isNotEmpty()){
                        sendFeedback(userInput.text.toString())
                    }
                }

                builder.setNegativeButton("No",null)
                builder.setNeutralButton("Cancel",null)
                setCancelable(false)
                show()
            }





        }


    }
}