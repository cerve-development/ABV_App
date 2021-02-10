package com.fair.tool_belt_abv.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.databinding.FragmentPrivacyBinding
import com.fair.tool_belt_abv.utils.Utility.go
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrivacyFragment: Fragment(R.layout.fragment_privacy) {

    private var _binding: FragmentPrivacyBinding? = null
    private val viewBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPrivacyBinding.bind(view)

        setUpToolbar()

    }

    private fun setUpToolbar() {
        viewBinding.apply {

            policyToolbar.setNavigationIcon(R.drawable.ic_arrow_back)
            policyToolbar.setNavigationOnClickListener {
                it.go(R.id.action_privacyFragment_to_calculatorFragment)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}