package com.fair.tool_belt_abv.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.data.Calculator
import com.fair.tool_belt_abv.data.database.FirebaseRealtime
import com.fair.tool_belt_abv.data.database.SharedPreference
import com.fair.tool_belt_abv.databinding.FragmentNewCalculatorBinding

import com.fair.tool_belt_abv.utils.toast

class CalculatorFragment: Fragment(R.layout.fragment_new_calculator) {

    private var _binding: FragmentNewCalculatorBinding? = null
    private val viewBinding get() = _binding!!
    private lateinit var pref : SharedPreference
    private lateinit var calculatorUnit: String
    private lateinit var calculatorEquation: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewCalculatorBinding.bind(view)


        viewBinding.apply {


            calculatorToolbar.inflateMenu(R.menu.menu_parent)
            calculatorToolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_settings -> {
                        Navigation.findNavController(view)
                            .navigate(R.id.action_calculatorFragment_to_containerFragment)
                        true }
                    R.id.action_policy -> {
                        Navigation.findNavController(view)
                            .navigate(R.id.action_calculatorFragment_to_privacyFragment)
                        true }

                    R.id.action_converter -> {
                        Navigation.findNavController(view)
                            .navigate(R.id.action_calculatorFragment_to_converterFragment)
                        true }

                    R.id.action_feedback -> {
                        context?.let {  FirebaseRealtime(it, layoutInflater).feedbackDialog()}
                        true }

                    else -> super.onOptionsItemSelected(item)
                }
            }

            context?.let { it ->
                Calculator().apply {

                    //retrieving any decisions the user made during previous sessions
                    pref = SharedPreference(it)
                    calculatorUnit = pref.get("calculatorUnit").toString()
                    calculatorEquation = pref.get("calculatorEquation").toString()

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

                    //toggling the unit to use for the app
                    chkBxSG.setOnClickListener {
                        pref.saveU("SG")
                        context?.toast("You are now using Specific Gravity")
                        onCalculate()
                    }
                    chkBxPlato.setOnClickListener {
                        pref.saveU("P")
                        context?.toast("You are now using Plato")
                        onCalculate()
                    }
                    chkBxBrix.setOnClickListener {
                        pref.saveU("B")
                        context?.toast("You are now using Brix")
                        onCalculate()
                    }

                    //toggling the equation to use  for the app
                    chkBxSimple.setOnClickListener {
                        pref.saveE("S")
                        context?.toast("You are now using a simple equation")
                        onCalculate()
                    }
                    chkBxAdvanced.setOnClickListener {
                        pref.saveE("A")
                        context?.toast("You are now using an advanced equation")
                        onCalculate()
                    }


                    eTxtOG.addTextChangedListener(object : TextWatcher {
                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                            onCalculate()

                        }
                        override fun afterTextChanged(p0: Editable?) {}
                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {} })

                    eTxtFG.addTextChangedListener(object : TextWatcher {
                        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                           onCalculate()

                        }
                        override fun afterTextChanged(p0: Editable?) {}
                        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                    })

                }
            }

            }

    }

    private fun onCalculate(){

        viewBinding.apply {

            Calculator().apply {

                calculatorUnit = pref.get("calculatorUnit").toString()
                calculatorEquation = pref.get("calculatorEquation").toString()

                val num1: String = if (eTxtOG.text.toString().startsWith(".")) {
                    "0${eTxtOG.text}" } else { eTxtOG.text.toString() }

                val num2: String = if (eTxtFG.text.toString().startsWith(".")) {
                    "0${eTxtFG.text}" } else { eTxtFG.text.toString() }

                val uE: String = calculatorEquation

                when {
                    chkBxSG.isChecked -> {
                        val calculation = sCalculator(num1, num2, uE)
                        txtAbvDisplay.text = getString(R.string.Abv, calculation.first)
                        txtAttenuationDisplay.text = getString(R.string.Apparent, calculation.second)
                        txtErrorDisplay.text = calculation.third
                    }
                    chkBxPlato.isChecked -> {
                        val calculation = pCalculator(num1, num2, uE)
                        txtAbvDisplay.text = getString(R.string.Abv, calculation.first)
                        txtAttenuationDisplay.text =
                            getString(R.string.Apparent, calculation.second)
                        txtErrorDisplay.text = calculation.third
                    }
                    chkBxBrix.isChecked -> {
                        val calculation = bCalculator(num1, num2, uE)
                        txtAbvDisplay.text = getString(R.string.Abv, calculation.first)
                        txtAttenuationDisplay.text =
                            getString(R.string.Apparent, calculation.second)
                        txtErrorDisplay.text = calculation.third
                    }
                }
            }

        }
    }
    override fun onResume() {
        super.onResume()
        viewBinding.apply {

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
            context?.let { calculatorUnit = SharedPreference(it).get("calculatorUnit").toString() }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}