package com.fair.tool_belt_abv.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.data.Converter
import com.fair.tool_belt_abv.databinding.FragmentNewConverterBinding

class ConverterFragment: Fragment(R.layout.fragment_new_converter) {

    private var _binding: FragmentNewConverterBinding? = null
    private val viewBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewConverterBinding.bind(view)


        viewBinding.apply {

            converterToolbar.setNavigationIcon(R.drawable.ic_arrow_back)
            converterToolbar.setNavigationOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_converterFragment_to_calculatorFragment)
            }


            Converter().apply {

                brixEtxt.setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {


                        brixEtxt.addTextChangedListener(object : TextWatcher {
                            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                                val brix = leadingZero(brixEtxt.text.toString())

                                if (brixEtxt.isFocused){
                                    val conversion = convert(brix, "brix")
                                    specificEtxt.setText(conversion.first)
                                    platoEtxt.setText(conversion.second)
                                }

                            }

                            override fun afterTextChanged(p0: Editable?) {}

                            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {} })

                    }
                }

                platoEtxt.setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        platoEtxt.addTextChangedListener(object : TextWatcher {
                            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                                val plato = leadingZero(platoEtxt.text.toString())

                                if (platoEtxt.isFocused){
                                    val conversion = convert(plato, "plato")
                                    brixEtxt.setText(conversion.first)
                                    specificEtxt.setText(conversion.second)

                                }

                            }

                            override fun afterTextChanged(p0: Editable?) {}

                            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {} })

                    }
                }

                specificEtxt.setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        specificEtxt.addTextChangedListener(object : TextWatcher {
                            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                                val specific = leadingZero(specificEtxt.text.toString())

                                if (specificEtxt.isFocused){
                                    val conversion = convert(specific, "specific")
                                    platoEtxt.setText(conversion.first)
                                    brixEtxt.setText(conversion.second)


                                }

                            }

                            override fun afterTextChanged(p0: Editable?) {}

                            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {} })
                    }
                }

            }


        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}