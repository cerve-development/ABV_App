package com.fair.tool_belt_abv.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.data.LifecycleSubscriptionsContainer
import com.fair.tool_belt_abv.data.SharedPreference
import com.fair.tool_belt_abv.databinding.FragmentNewContainerBinding
import com.fair.tool_belt_abv.utils.Utility.go
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContainerFragment: Fragment(R.layout.fragment_new_container) {

    private var _binding : FragmentNewContainerBinding? = null
    private val viewBinding get() = _binding!!

    private var subscriptionsContainer : LifecycleSubscriptionsContainer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewContainerBinding.bind(view)
        subscriptionsContainer = LifecycleSubscriptionsContainer(requireContext(), lifecycle)

        setUpToolbar()
        parentFragmentManager.beginTransaction()
            .replace(R.id.content, PSFragment() )
            .commit()

        subscriptionsContainer?.registerPreference("calculatorAppTheme") {
            setThemeMode()
        }

    }

    private fun setThemeMode() {
        when (SharedPreference(requireContext()).get("calculatorAppTheme").toString()) {

            "DM" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            "LM" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            "SD" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }

            else -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }

        }
    }

    private fun setUpToolbar() {
        viewBinding.apply {

            settingToolbar.setNavigationIcon(R.drawable.ic_arrow_back)
            settingToolbar.setNavigationOnClickListener {
                it.go(R.id.action_containerFragment_to_calculatorFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}