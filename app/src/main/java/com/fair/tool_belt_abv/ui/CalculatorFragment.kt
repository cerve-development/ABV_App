package com.fair.tool_belt_abv.ui

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.data.CalculatorViewModel
import com.fair.tool_belt_abv.data.SharedPreference
import com.fair.tool_belt_abv.databinding.FragmentNewCalculatorBinding
import com.fair.tool_belt_abv.utils.Calculator
import com.fair.tool_belt_abv.utils.Utility.go
import com.fair.tool_belt_abv.utils.Utility.isLeadingDecimal
import com.fair.tool_belt_abv.utils.Utility.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CalculatorFragment: Fragment(R.layout.fragment_new_calculator) {

    private var _binding: FragmentNewCalculatorBinding? = null
    private val viewBinding get() = _binding!!

    private var pref : SharedPreference? = null
    private var calculatorUnit: String? = null
    private var calculatorEquation: String? = null

    private val calculatorViewModel: CalculatorViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewCalculatorBinding.bind(view)
        pref = SharedPreference(requireContext()).apply {

            calculatorUnit = get("calculatorUnit").toString()
            calculatorEquation = get("calculatorEquation").toString()

        }

        viewBinding.apply {

            Calculator.apply {

                when(calculatorUnit) {
                    "SG" -> chkBxSG.isChecked = true
                    "P" -> chkBxPlato.isChecked = true
                    "B" -> chkBxBrix.isChecked = true
                    else -> chkBxSG.isChecked = true
                }

                when(calculatorEquation) {
                    "S" -> chkBxSimple.isChecked = true
                    "A" -> chkBxAdvanced.isChecked = true
                    else -> chkBxSimple.isChecked = true
                }

            }

            setUpToolbar()
            setUpUnitToggling()
            setUpEquationToggling()

            eTxtOG.doAfterTextChanged {
                onCalculate()
            }
            eTxtFG.doAfterTextChanged {
                onCalculate()
            }

        }

    }

    private fun setUpToolbar() {
        viewBinding.apply {

            calculatorToolbar.apply {
                inflateMenu(R.menu.menu_parent)
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.action_settings -> {
                            go(R.id.action_calculatorFragment_to_containerFragment)
                            true }
                        R.id.action_policy -> {
                            go(R.id.action_calculatorFragment_to_privacyFragment)
                            true }

                        R.id.action_converter -> {
                            go(R.id.action_calculatorFragment_to_converterFragment)
                            true }

                        R.id.action_feedback -> {
                            calculatorViewModel.beginSendFeedBack(context)
                            true
                        }

                        else -> super.onOptionsItemSelected(item)
                    }
                }
            }

        }
    }

    private fun setUpUnitToggling() {
        viewBinding.apply {

            pref?.apply {

                chkBxSG.setOnClickListener {
                    saveU("SG")
                    context?.toast("You are now using Specific Gravity")
                    onCalculate()
                }
                chkBxPlato.setOnClickListener {
                    saveU("P")
                    context?.toast("You are now using Plato")
                    onCalculate()
                }
                chkBxBrix.setOnClickListener {
                    saveU("B")
                    context?.toast("You are now using Brix")
                    onCalculate()
                }

            }
        }
    }

    private fun setUpEquationToggling() {

        viewBinding.apply {

            pref?.apply {

                chkBxSimple.setOnClickListener {
                    saveE("S")
                    context?.toast("You are now using a simple equation")
                    onCalculate()
                }
                chkBxAdvanced.setOnClickListener {
                    saveE("A")
                    context?.toast("You are now using an advanced equation")
                    onCalculate()
                }

            }

        }
    }

    private fun onCalculate() {

        viewBinding.apply {

            Calculator.apply {

                pref?.apply {
                    calculatorUnit = get("calculatorUnit").toString()
                    calculatorEquation = get("calculatorEquation").toString()
                }

                val num1: String? = eTxtOG.isLeadingDecimal()

                val num2: String? = eTxtFG.isLeadingDecimal()

                when {
                    chkBxSG.isChecked -> {
                        val calculation = calculatorEquation.sCalculator(num1, num2)
                        abvDisplay.text = getString(R.string.abv, calculation.first)
                        attenuationDisplay.text = getString(R.string.attenuation, calculation.second)
                        warningDisplay.text = getString(R.string.warning, calculation.third)
                    }
                    chkBxPlato.isChecked -> {
                        val calculation = calculatorEquation.pCalculator(num1, num2)
                        abvDisplay.text = getString(R.string.abv, calculation.first)
                        attenuationDisplay.text = getString(R.string.attenuation, calculation.second)
                        warningDisplay.text = getString(R.string.warning, calculation.third)

                    }
                    chkBxBrix.isChecked -> {
                        val calculation = calculatorEquation.bCalculator(num1, num2)
                        abvDisplay.text = getString(R.string.abv, calculation.first)
                        attenuationDisplay.text = getString(R.string.attenuation, calculation.second)
                        warningDisplay.text = getString(R.string.warning, calculation.third)

                    }
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        viewBinding.apply {
            context?.let { calculatorUnit = SharedPreference(it).get("calculatorUnit").toString() }

            when(calculatorUnit) {
                "SG" -> chkBxSG.isChecked = true
                "P" -> chkBxPlato.isChecked = true
                "B" -> chkBxBrix.isChecked = true
                else -> chkBxSG.isChecked = true
            }

            when(calculatorEquation) {
                "S" -> chkBxSimple.isChecked = true
                "A" -> chkBxAdvanced.isChecked = true
                else -> chkBxSimple.isChecked = true
            }

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}