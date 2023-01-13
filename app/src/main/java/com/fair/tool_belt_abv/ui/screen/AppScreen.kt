package com.fair.tool_belt_abv.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.smallTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.fair.tool_belt_abv.model.AppPreferences
import com.fair.tool_belt_abv.ui.component.SimpleAbvNavigationBar
import com.fair.tool_belt_abv.ui.component.ThemedDivider
import com.fair.tool_belt_abv.ui.navigation.NavigationGraph
import com.fair.tool_belt_abv.ui.navigation.TopLevelDestinationGraph
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
            Column(
                modifier = Modifier
                    .background(colors.inversePrimary)
                    .statusBarsPadding()
            ) {
                TopAppBar(
                    colors = smallTopAppBarColors(containerColor = Color.Transparent),
                    navigationIcon = {
                        IconButton(
                            onClick = { context.sendEmail(subject = EMAIL_SUBJECT_SUPPORT) }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.HelpOutline,
                                contentDescription = null
                            )
                        }
                    },
                    title = { },
                    actions = {
                        IconButton(
                            onClick = {
                                navController.navigate(TopLevelDestinationGraph.SETTINGS)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Settings,
                                contentDescription = null
                            )
                        }
                    }
                )
                ThemedDivider()
            }
        },
        bottomBar = {

            SimpleAbvNavigationBar(
                modifier = Modifier
                    .background(colors.primaryContainer)
                    .navigationBarsPadding(),
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