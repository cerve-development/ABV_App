package com.fair.tool_belt_abv.data

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

open class SubscriptionsContainer (val context: Context) {
    private val listeners = mutableListOf<SharedPreferences.OnSharedPreferenceChangeListener>()

    fun registerPreference(preferenceKey: String, action: () -> Unit) {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (preferenceKey == key) {
                action()
            }
        }

        listeners.add(listener)
        PreferenceManager.getDefaultSharedPreferences(context)
            .registerOnSharedPreferenceChangeListener(listener)
        action()
    }

    open fun clearSubscriptions() {
        listeners.forEach {
            PreferenceManager.getDefaultSharedPreferences(context)
                .unregisterOnSharedPreferenceChangeListener(it)
        }
    }
}