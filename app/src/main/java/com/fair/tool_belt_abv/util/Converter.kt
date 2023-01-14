package com.fair.tool_belt_abv.util

object Converter {

    fun convert(value: String, focused: String): Pair<String, String> {
        Equation.apply {
            return if (value.isNotEmpty()) {
                val valueTrue = value.toDouble()
                return when (focused) {
                    "B" -> {
                        /**
                         * PAIR(SG, PLATO)
                         */
                        Pair(
                            zeroed(bToS(valueTrue).toString()),
                            zeroed(sToP(bToS(valueTrue)).toString())
                        )
                    }

                    "P" -> {
                        /**
                         * PAIR(BRIX, SG)
                         */
                        Pair(
                            zeroed(sToB(pToS(valueTrue)).toString()),
                            zeroed(pToS(valueTrue).toString())
                        )
                    }

                    "SG" -> {
                        /**
                         * PAIR(PLATO, BRIX)
                         */
                        Pair(
                            zeroed(sToP(valueTrue).toString()),
                            zeroed(sToB(valueTrue).toString())
                        )
                    }
                    else -> Pair("", "")
                }
            } else { Pair("", "") }
        }
    }

    private fun zeroed(text: String): String {
        return if (text.toDouble() <= 0) {
            "0.0000"
        } else { text }
    }
}
