package com.fair.tool_belt_abv.utils

object Converter {

    fun convert(value: String, focused: String): Pair<String, String> {

        Equation.apply {

            return if (value.isNotEmpty()){
                val valueTrue = value.toDouble()
                return when (focused) {
                    "brix" -> {
                        Pair(
                            zeroed(bToS(valueTrue).toString()),
                            zeroed(sToP(bToS(valueTrue)).toString())
                        )
                    }

                    "plato" -> {
                        Pair(
                            zeroed(sToB(pToS(valueTrue)).toString()),
                            zeroed(pToS(valueTrue).toString())
                        )
                    }

                    "specific" -> {
                        Pair(
                            zeroed(sToP(valueTrue).toString()),
                            zeroed(sToB(valueTrue).toString())
                        )
                    }
                    else -> Pair("", "")
                } }
            else{
                Pair("", "") }
        }
    }

    private fun zeroed(text: String): String{

        return if ( text.toDouble() <= 0){
            "0.0000"
        }else{text}


    }

    fun leadingZero(text: String): String {
        return when {
            text.startsWith(".") -> { "0$text" }
            text.isEmpty() -> { "0.0" }
            else -> { text }
        }
    }
}