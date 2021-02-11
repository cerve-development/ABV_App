package com.fair.tool_belt_abv.data

import android.content.Context
import androidx.lifecycle.ViewModel
import com.fair.tool_belt_abv.data.database.FirebaseRealtimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val repository: FirebaseRealtimeRepository
): ViewModel() {

    fun beginSendFeedBack(context: Context) {
        repository.feedbackDialog(context)
    }


}