package com.fair.tool_belt_abv.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.fair.tool_belt_abv.model.AbvEquation
import com.fair.tool_belt_abv.model.AbvUnit
import com.fair.tool_belt_abv.ui.screen.CalculatorScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
) {

    NavHost(
        modifier = modifier.padding(sizes.small),
        startDestination = startDestination,
        navController = navController
    ) {

        composable(TopLevelDestinationGraph.CALCULATOR.route) {


            CalculatorScreen(
                abvUnit = AbvUnit.SIMPLE_GRAVITY,
                abvEquation = AbvEquation.SIMPLE
            )
        }

        composable(TopLevelDestinationGraph.CONVERTER.route) {

        }

    }

}