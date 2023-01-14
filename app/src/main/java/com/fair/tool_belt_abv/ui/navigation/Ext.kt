package com.fair.tool_belt_abv.ui.navigation

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy

fun NavDestination?.isTopLevelDestinationInHierarchy(destination: String) = this
    ?.hierarchy
    ?.any { it.route?.contains(destination, true) ?: false } ?: false
