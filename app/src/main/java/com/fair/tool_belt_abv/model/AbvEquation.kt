package com.fair.tool_belt_abv.model

import androidx.annotation.StringRes
import com.fair.tool_belt_abv.R

enum class AbvEquation(
    @StringRes val textId: Int,
    @StringRes val subtextId: Int? = null
) {
    S(
        textId = R.string.EQUATION_TEXT_simple,
        subtextId = R.string.EQUATION_SUBTEXT_simple
    ),
    A(
        textId = R.string.EQUATION_TEXT_advance,
        subtextId = R.string.EQUATION_SUBTEXT_simple
    )
}