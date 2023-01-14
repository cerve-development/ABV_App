package com.fair.tool_belt_abv.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.ChangeCircle
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.ChangeCircle
import androidx.compose.ui.graphics.vector.ImageVector
import com.fair.tool_belt_abv.R

enum class TopLevelDestinationGraph(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val labelId: Int,
    val route: String
) {

    CALCULATOR(
        selectedIcon = Icons.Filled.Calculate,
        unselectedIcon = Icons.Outlined.Calculate,
        labelId = R.string.NAV_DESTINATION_calculator,
        route = "route_calculator"
    ),
    CONVERTER(
        selectedIcon = Icons.Filled.ChangeCircle,
        unselectedIcon = Icons.Outlined.ChangeCircle,
        labelId = R.string.NAV_DESTINATION_converter,
        route = "route_converter"
    );

    companion object {
        const val SETTINGS = "route_settings"
    }
}
