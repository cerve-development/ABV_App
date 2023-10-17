package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fair.tool_belt_abv.ui.navigation.TopLevelDestinationGraph

@Composable
fun SimpleAbvNavigationBar(
    currentDestination: String?,
    modifier: Modifier = Modifier,
    onNavigateToDestination: (TopLevelDestinationGraph) -> Unit = { }
) {
    Column(modifier = modifier) {
        ThemedDivider()

        NavigationBar {
            TopLevelDestinationGraph.entries.forEach { destination ->

                val selected = currentDestination == destination.route
                val icon = if (selected) {
                    destination.selectedIcon
                } else destination.unselectedIcon

                val label = stringResource(id = destination.labelId)

                NavigationBarItem(
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


@Preview
@Composable
fun SimpleAbvNavigationBarPreview() {
    SimpleAbvNavigationBar(TopLevelDestinationGraph.CALCULATOR.route)
}
