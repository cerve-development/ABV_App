package com.fair.tool_belt_abv.model

import androidx.annotation.StringRes
import com.fair.tool_belt_abv.R

enum class AbvUnit(@StringRes val textId: Int) {
    SG(textId = R.string.UNIT_TEXT_gravity),
    P(textId = R.string.UNIT_TEXT_plato),
    B(textId = R.string.UNIT_TEXT_brix)
}