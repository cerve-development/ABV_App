package com.cerve.abv.shared.model

import kotlinx.datetime.Clock
import java.text.SimpleDateFormat
import java.util.*

sealed class AbvTestEquation(
    val name: String,
    val equation: String,
    val updatedAt: Long?,
    val type: EquationType
) {

    data object Simple : AbvTestEquation(
        name = "simple",
        equation = "(${StaticValues.OG} - ${StaticValues.FG}) * 131.25",
        updatedAt = null,
        type = EquationType.Default
    )

    data object Advance : AbvTestEquation(
        name = "advance",
        equation = "76.08 * (${StaticValues.OG} - ${StaticValues.FG}) / (1.775 - ${StaticValues.OG}) * (${StaticValues.FG} / 0.794)",
        updatedAt = null,
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

    fun timeStamp(timeMillis: Long? = updatedAt) : String? {
        return timeMillis?.let { millis ->
            val formatter = SimpleDateFormat(
                "MMM d, yyyy",
                Locale.ENGLISH
            )
            formatter.format(millis)
        }
    }
}
