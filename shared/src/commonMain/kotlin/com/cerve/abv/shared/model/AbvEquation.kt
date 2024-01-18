package com.cerve.abv.shared.model

import com.cerve.abv.shared.domain.NewEquationUseCase
import kotlinx.datetime.Clock
import java.text.SimpleDateFormat
import java.util.*

sealed class AbvEquation(
    val name: String,
    val equation: String,
    val updatedAt: Long?,
    val isValid: Boolean = NewEquationUseCase.isValid(equation),
    val type: EquationType
) {

    data object Simple : AbvEquation(
        name = "simple",
        equation = "(${StaticValues.OG} - ${StaticValues.FG}) * 131.25",
        updatedAt = null,
        type = EquationType.Default
    )

    data object Advance : AbvEquation(
        name = "advance",
        equation = "76.08 * (${StaticValues.OG} - ${StaticValues.FG}) / (1.775 - ${StaticValues.OG}) * (${StaticValues.FG} / 0.794)",
        updatedAt = null,
        type = EquationType.Default
    )

    data class Entity(
        private val dbName: String,
        private val dbEquation: String,
        private val dbUpdatedAt: Long = Clock.System.now().toEpochMilliseconds()
    ) : AbvEquation(dbName, dbEquation, dbUpdatedAt, type = EquationType.Custom)


    enum class EquationType {
        Default,
        Custom
    }
    enum class StaticValues {
        OG, FG
    }
    fun updatedAtOrNow() = updatedAt ?: Clock.System.now().toEpochMilliseconds()

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
