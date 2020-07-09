package com.fair.tool_belt_abv.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.databinding.FragmentNewContainerBinding

class ContainerFragment: Fragment(R.layout.fragment_new_container) {

    private var _binding : FragmentNewContainerBinding? = null
    private val viewBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewContainerBinding.bind(view)

        viewBinding.apply {

            settingToolbar.setNavigationIcon(R.drawable.ic_arrow_back)
            settingToolbar.setNavigationOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_containerFragment_to_calculatorFragment)
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.content, PSFragment() )
                .commit()

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}