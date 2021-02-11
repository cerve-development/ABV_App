package com.fair.tool_belt_abv.data.database


import android.content.Context
import android.util.Log
import com.fair.tool_belt_abv.utils.FeedbackDialog.showFeedbackDialog
import javax.inject.Inject

class FirebaseRealtimeRepository @Inject constructor() {


    fun feedbackDialog(context: Context) {


        context.showFeedbackDialog { message ->
            sendFeedback(message)
        }




//        return context.let {
//
//            val builder = MaterialAlertDialogBuilder(it)
//
//            builder.apply {
//
//                setTitle("Would you like to request a feature?\n")
//                val customLayout = inflater.inflate(R.layout.dialog_feedback, null)
//                setView(customLayout)
//                setPositiveButton("Submit"){_,_->
//                    val userInput = customLayout.findViewById<TextInputEditText>(R.id.feedbackText)
//
//                    if(userInput.text.toString().isNotEmpty()){
//                        sendFeedback(userInput.text.toString())
//                    }
//                }
//
//                builder.setNegativeButton("No",null)
//                builder.setNeutralButton("Cancel",null)
//                setCancelable(false)
//                show()
//            }
//
//
//
//
//
//        }


    }

    private fun sendFeedback(feedback: String?){

        Log.d("Hello", "$feedback")
//        val database = FirebaseDatabase.getInstance().reference
//
//            database.child("Feedback").child(UUID.randomUUID().toString()).setValue(feedback)
//                .addOnSuccessListener {
//                    context.toast("Thank you for your feedback!")
//                }
//                .addOnFailureListener {
//                    context.toast("uh oh, please connect to the internet and try again")
//                }
//

    }
}