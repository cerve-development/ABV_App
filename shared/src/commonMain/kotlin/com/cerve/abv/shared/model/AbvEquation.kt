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
    val updatedAt: Long
) {

    data object Default : AbvTestEquation(
        name = "default",
        equation = "",
        updatedAt = Clock.System.now().toEpochMilliseconds()
    )

    data class Entity(
        val dbName: String,
        val dbEquation: String,
        val dbUpdatedAt: Long = Clock.System.now().toEpochMilliseconds()
    ) : AbvTestEquation(dbName, dbEquation, dbUpdatedAt)

}
