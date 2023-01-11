package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import com.fair.tool_belt_abv.ui.navigation.TopLevelDestinationGraph
import com.fair.tool_belt_abv.ui.navigation.isTopLevelDestinationInHierarchy

@Composable
fun SimpleAbvNavigationBar(
    currentDestination: NavDestination?,
    destinations: List<TopLevelDestinationGraph>,
    modifier: Modifier = Modifier,
    onNavigateToDestination: (TopLevelDestinationGraph) -> Unit = { }
) {

    Column(modifier = modifier) {

        ThemedDivider()

        NavigationBar(containerColor = Color.Transparent) {

            destinations.forEach { destination ->

                val selected = currentDestination.isTopLevelDestinationInHierarchy(destination.name)
                val icon = if (selected) destination.selectedIcon else destination.unselectedIcon
                val label = stringResource(id = destination.labelId)

                NavigationBarItem(
                    modifier = Modifier,
                    selected = selected,
                    icon = {
                        Icon(
                            imageVector = icon,
                            contentDescription = label
                        )
                    },
                    label = { Text(text = label) },
                    onClick = { onNavigateToDestination(destination) }
                )

            }
        }

    }

}