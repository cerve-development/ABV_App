package com.fair.tool_belt_abv.model

import androidx.annotation.StringRes
import com.fair.tool_belt_abv.R

enum class AbvUnit(
    @StringRes val textId: Int,
    @StringRes val subtextId: Int? = null
) {
    SG(
        textId = R.string.UNIT_TEXT_gravity,
    ),
    P(
        textId = R.string.UNIT_TEXT_plato
    ),
    BRIX(
        textId = R.string.UNIT_TEXT_brix
    )
}