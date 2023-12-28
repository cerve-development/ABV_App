package com.fair.tool_belt_abv.ui.navigation

import androidx.annotation.StringRes
import com.fair.tool_belt_abv.R

enum class CalculatorDestinationGraph(
    @StringRes val nameId: Int
) {
    Result(R.string.NAV_DESTINATION_result),
    Equation(R.string.NAV_DESTINATION_equation),
//    Note(R.string.NAV_DESTINATION_notes)
}