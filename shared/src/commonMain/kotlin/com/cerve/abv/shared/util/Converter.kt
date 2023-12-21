package com.cerve.abv.shared.util

object Converter {

    fun convert(value: String, focused: String): Pair<String, String> {
        Equation.apply {
            return if (value.isNotEmpty()) {
                val valueTrue = value.toDouble()
                return when (focused) {
                    "B" -> {
                        /**
                         * PAIR(SG, P)
                         */
                        Pair(
                            zeroed(bToS(valueTrue).toString()),
                            zeroed(sToP(bToS(valueTrue)).toString())
                        )
                    }

                    "P" -> {
                        /**
                         * PAIR(B, SG)
                         */
                        Pair(
                            zeroed(sToB(pToS(valueTrue)).toString()),
                            zeroed(pToS(valueTrue).toString())
                        )
                    }

                    "SG" -> {
                        /**
                         * PAIR(P, B)
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
