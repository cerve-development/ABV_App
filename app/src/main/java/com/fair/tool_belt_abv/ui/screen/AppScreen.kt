package com.fair.tool_belt_abv.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.fair.tool_belt_abv.model.AppPreferences
import com.fair.tool_belt_abv.ui.component.SimpleAbvNavigationBar
import com.fair.tool_belt_abv.ui.component.SimpleAbvTopAppBar
import com.fair.tool_belt_abv.ui.navigation.NavigationGraph
import com.fair.tool_belt_abv.ui.navigation.TopLevelDestinationGraph
import com.fair.tool_belt_abv.ui.navigation.TopLevelDestinationGraph.Companion.SETTINGS
import com.fair.tool_belt_abv.util.EMAIL_SUBJECT_SUPPORT
import com.fair.tool_belt_abv.util.sendEmail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(
    appPreferences: AppPreferences,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val currentDestination by navController.currentBackStackEntryAsState()
    val context = LocalContext.current

    Scaffold(
        modifier = modifier,
        topBar = {
            SimpleAbvTopAppBar(
                modifier = Modifier.background(colors.primaryContainer),
                onNavigationButtonClick = { context.sendEmail(subject = EMAIL_SUBJECT_SUPPORT) },
                onActionButtonClick = { navController.navigate(SETTINGS) }
            )
        },
        bottomBar = {
            SimpleAbvNavigationBar(
                currentDestination = currentDestination?.destination,
                destinations = TopLevelDestinationGraph.values().asList(),
                onNavigateToDestination = { destination ->
                    navController.navigate(destination.route)
                }
            )
        }
    ) { innerPadding ->
        NavigationGraph(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = TopLevelDestinationGraph.CALCULATOR.route,
            preferences = appPreferences
        )
    }
}

@Preview
@Composable
fun AppScreenPreview() {
    AppScreen(appPreferences = AppPreferences())
}
