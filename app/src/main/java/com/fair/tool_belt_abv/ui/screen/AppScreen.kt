package com.fair.tool_belt_abv.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fair.tool_belt_abv.ui.component.SimpleAbvNavigationBar
import com.fair.tool_belt_abv.ui.navigation.NavigationGraph
import com.fair.tool_belt_abv.ui.navigation.TopLevelDestinationGraph

@Composable
fun AppScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = remember(backStackEntry) { backStackEntry?.destination?.route }
    val isTopLevelDestination by remember(currentDestination) {
        derivedStateOf {
             TopLevelDestinationGraph.entries.find { it.route == currentDestination } != null
        }
    }

    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0),
        bottomBar = {
            AnimatedVisibility(visible = isTopLevelDestination) {
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
            navController = navController,
            startDestination = TopLevelDestinationGraph.CALCULATOR.route
        )
    }
}

@Preview
@Composable
fun AppScreenPreview() {
    AppScreen()
}
