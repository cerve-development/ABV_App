package com.fair.tool_belt_abv.data.database

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class SharedPreference(context: Context) {

    private val defaultPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveE(value: String){
        val editor: SharedPreferences.Editor = defaultPreferences.edit()
        editor.putString("calculatorEquation", value)
        editor.apply()
    }
    fun saveU(value: String){
        val editor: SharedPreferences.Editor = defaultPreferences.edit()
        editor.putString("calculatorUnit", value)
        editor.apply()
    }

    val get = { KEY_NAME: String -> defaultPreferences.getString(KEY_NAME, "")}
}