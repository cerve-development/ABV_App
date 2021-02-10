package com.fair.tool_belt_abv.ui

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.databinding.FragmentNewConverterBinding
import com.fair.tool_belt_abv.utils.Converter
import com.fair.tool_belt_abv.utils.Utility.go
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConverterFragment: Fragment(R.layout.fragment_new_converter) {

    private var _binding: FragmentNewConverterBinding? = null
    private val viewBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewConverterBinding.bind(view)

        setUpToolbar()
        setUpConverterToggling()

    }

    private fun setUpToolbar() {
        viewBinding.apply {

            converterToolbar.setNavigationIcon(R.drawable.ic_arrow_back)
            converterToolbar.setNavigationOnClickListener {
                it.go(R.id.action_converterFragment_to_calculatorFragment)
            }
        }
    }
    private fun setUpConverterToggling() {
        viewBinding.apply {

            Converter.apply {

                brixEtxt.setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {

                        brixEtxt.doAfterTextChanged {

                            val brix = leadingZero(brixEtxt.text.toString())

                            if (brixEtxt.isFocused){
                                val conversion = convert(brix, "brix")
                                specificEtxt.setText(conversion.first)
                                platoEtxt.setText(conversion.second)
                            }

                        }

                    }
                }

                platoEtxt.setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        platoEtxt.doAfterTextChanged {
                            val plato = leadingZero(platoEtxt.text.toString())

                            if (platoEtxt.isFocused){
                                val conversion = convert(plato, "plato")
                                brixEtxt.setText(conversion.first)
                                specificEtxt.setText(conversion.second)

                            }
                        }

                    }
                }

                specificEtxt.setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        specificEtxt.doAfterTextChanged {
                            val specific = leadingZero(specificEtxt.text.toString())

                            if (specificEtxt.isFocused){
                                val conversion = convert(specific, "specific")
                                platoEtxt.setText(conversion.first)
                                brixEtxt.setText(conversion.second)


                            }
                        }
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