package com.cerve.abv.shared.model

import kotlinx.datetime.Clock

enum class AbvEquation {
    S,
    A,
    C
}

sealed class AbvTestEquation(
    val name: String,
    val equation: String,
    val updatedAt: Long,
    val type: EquationType
) {

    data object Simple : AbvTestEquation(
        name = "simple",
        equation = "(${StaticValues.OG} - ${StaticValues.FG}) * 131.25",
        updatedAt = Clock.System.now().toEpochMilliseconds(),
        type = EquationType.Default
    )

    data object Advance : AbvTestEquation(
        name = "advance",
        equation = "76.08 * (${StaticValues.OG} - ${StaticValues.FG}) / (1.775 - ${StaticValues.OG}) * (${StaticValues.FG} / 0.794)",
        updatedAt = Clock.System.now().toEpochMilliseconds(),
        type = EquationType.Default
    )

    data class Entity(
        val dbName: String,
        val dbEquation: String,
        val dbUpdatedAt: Long = Clock.System.now().toEpochMilliseconds()
    ) : AbvTestEquation(dbName, dbEquation, dbUpdatedAt, type = EquationType.Custom)

    enum class EquationType {
        Default,
        Custom
    }
    enum class StaticValues {
        OG, FG
    }
}
