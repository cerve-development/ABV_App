package com.fair.tool_belt_abv.ui.model

enum class KeyPadValue(val label: String, val text: String = label) {
    Sine("sin", "sin("), Cosine("cos", "cos("), Tangent("tan", "tan("), Factorial("!"), Euler("e"),
    Logarithm("log", "log("), NaturalLogarithm("ln", "ln("), OpenParentheses("("), CloseParentheses(")"), Division("÷", "/"),

    Modulo("%"),

    Number7("7"), Number8("8"), Number9("9"), Multiplication("×", "*"),
    Exponent("^"), Number4("4"), Number5("5"), Number6("6"), Subtraction("-"),
    SquareRoot("√"), Number1("1"), Number2("2"), Number3("3"), Addition("+"),
    Pi("π"), Number00("00"), Number0("0"), Decimal("."), Equal("=");

}
