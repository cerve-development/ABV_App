package com.fair.tool_belt_abv.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.fair.tool_belt_abv.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PSFragment: PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.ps_settings, rootKey)
    }

}