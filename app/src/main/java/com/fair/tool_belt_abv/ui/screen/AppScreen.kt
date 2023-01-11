package com.fair.tool_belt_abv.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fair.tool_belt_abv.ui.component.SimpleAbvNavigationBar
import com.fair.tool_belt_abv.ui.navigation.NavigationGraph
import com.fair.tool_belt_abv.ui.navigation.TopLevelDestinationGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
) {


    Scaffold(
        modifier = modifier,
        topBar = {

        },
        bottomBar = {
            val currentDestination by navController.currentBackStackEntryAsState()

            SimpleAbvNavigationBar(
                currentDestination = currentDestination?.destination,
                destinations = TopLevelDestinationGraph.values().asList()
            )
        }
    ) { innerPadding ->
        NavigationGraph(
            modifier = Modifier.padding(innerPadding),
            navController = navController
        )
    }

}

@Preview
@Composable
fun AppScreenPreview() {
    AppScreen()
}