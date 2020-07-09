package com.fair.tool_belt_abv.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.databinding.FragmentPrivacyBinding

class PrivacyFragment: Fragment(R.layout.fragment_privacy) {

    private var _binding: FragmentPrivacyBinding? = null
    private val viewBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentPrivacyBinding.bind(view)

        viewBinding.apply {

            policyToolbar.setNavigationIcon(R.drawable.ic_arrow_back)
            policyToolbar.setNavigationOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_privacyFragment_to_calculatorFragment)
            }



        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}