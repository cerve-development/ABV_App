package com.fair.tool_belt_abv.ui

enum class KeyPadValue(val value: String) {
    Sine("sin"), Cosine("cos"), Tangent("tan"), Radian("rad"), Degree("deg"),

    Logarithm("log"), NaturalLogarithm("ln"), OpenParentheses("("), CloseParentheses(")"), Division("÷"),

//    Factorial("!"),
//    Clear("C"),
    Modulo("%"),
//
//
//
//
//
//
//
//
//    Euler("e"),

    Number7("7"), Number8("8"), Number9("9"), Multiplication("×"),
    Exponent("^"),Number4("4"), Number5("5"), Number6("6"), Subtraction("−"),
    SquareRoot("√"), Number1("1"), Number2("2"), Number3("3"), Addition("+"),
    Pi("π"),Number00("00"), Number0("0"), Decimal("."),Equal("="),

    ;

    companion object {
        fun chunked(size: Int = 5) : List<List<KeyPadValue>> {
            return entries.chunked(size)
        }
    }

}