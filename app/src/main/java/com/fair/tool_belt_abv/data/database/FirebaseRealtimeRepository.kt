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

    }

    private fun sendFeedback(feedback: String?){

        Log.d("Hello", "$feedback")

    }
}