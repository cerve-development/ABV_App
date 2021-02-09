package com.fair.tool_belt_abv.utils

import android.content.Context
import android.widget.Toast

object Utility {

    fun Context.toast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}