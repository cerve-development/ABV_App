package com.fair.tool_belt_abv.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

enum class LowerLevelDestinationGraph(
    val route: String,
    val args : String? = null
) {
    EQUATION("equation_creator_route", "EQUATION_ARGS");

    companion object {
        fun LowerLevelDestinationGraph.asArgs() : String {
            return "$route?{$args}"
        }
        fun LowerLevelDestinationGraph.toArgs(args: String? = null) : String {
            return "$route?$args"
        }

        fun LowerLevelDestinationGraph.stringArguments() = args?.let {listOf(
            navArgument(it) {
                type = NavType.StringType
                nullable = true
            })
        } ?: emptyList()
    }
}