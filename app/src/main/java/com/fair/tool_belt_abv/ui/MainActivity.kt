package com.fair.tool_belt_abv.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate.*
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.data.SharedPreference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set preferences on first log in
        SharedPreference(this).apply {

            if (get("calculatorUnit").isNullOrEmpty()) {
                saveU("SG")
            }

            if (get("calculatorEquation").isNullOrEmpty()) {
                saveE("S")
            }

        }

        when (SharedPreference(this).get("calculatorAppTheme").toString()) {

            "DM" -> {
                setDefaultNightMode(MODE_NIGHT_YES)
                delegate.applyDayNight()
            }
            "LM" -> {
                setDefaultNightMode(MODE_NIGHT_NO)
                delegate.applyDayNight()
            }
            "SD" -> {
                setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
                delegate.applyDayNight()
            }

            else -> {
                setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
                delegate.applyDayNight()
            }

        }

    }
}
