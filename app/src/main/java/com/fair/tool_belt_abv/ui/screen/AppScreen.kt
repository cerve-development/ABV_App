package com.fair.tool_belt_abv.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cerve.abv.shared.model.AppTheme
import com.fair.tool_belt_abv.ui.component.SimpleAbvNavigationBar
import com.fair.tool_belt_abv.ui.navigation.LowerLevelDestinationGraph
import com.fair.tool_belt_abv.ui.navigation.NavigationGraph
import com.fair.tool_belt_abv.ui.navigation.TopLevelDestinationGraph

@Composable
fun AppScreen(
    inDarkMode: Boolean,
    appTheme: AppTheme,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    var isTopLevelDestination = rememberSaveable { mutableStateOf(true) }

    val currentDestination = (backStackEntry?.destination?.route)

    LaunchedEffect(backStackEntry) {
        isTopLevelDestination.value = when (currentDestination) {
            LowerLevelDestinationGraph.EQUATION.route -> false
            else -> true
        }
    }
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0),
        bottomBar = {

            AnimatedVisibility(visible = isTopLevelDestination.value) {

                SimpleAbvNavigationBar(
                    currentDestination = currentDestination,
                    onNavigateToDestination = { destination ->
                        navController.navigate(destination.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // re-selecting the same item
                            launchSingleTop = true
                            // Restore state when re-selecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }

        }
    ) { innerPadding ->
        NavigationGraph(
            modifier = Modifier.padding(innerPadding),
            inDarkMode = inDarkMode,
            appTheme = appTheme,
            navController = navController,
            startDestination = TopLevelDestinationGraph.CALCULATOR.route
        )
    }
}

@Preview
@Composable
fun AppScreenPreview() {
    AppScreen(true, AppTheme.LEGACY)
}
