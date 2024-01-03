package com.fair.tool_belt_abv.util

object Converter {

    fun convert(value: String, focused: String): Pair<String, String> {
        Equation.apply {
            return if (value.isNotEmpty()) {
                val valueTrue = value.toDouble()
                return when (focused) {
                    "Brix" -> {
                        /**
                         * PAIR(SG, Plato)
                         */
                        Pair(
                            zeroed(bToS(valueTrue).toString()),
                            zeroed(sToP(bToS(valueTrue)).toString())
                        )
                    }

                    "Plato" -> {
                        /**
                         * PAIR(Brix, SG)
                         */
                        Pair(
                            zeroed(sToB(pToS(valueTrue)).toString()),
                            zeroed(pToS(valueTrue).toString())
                        )
                    }

                    "SG" -> {
                        /**
                         * PAIR(Plato, Brix)
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
