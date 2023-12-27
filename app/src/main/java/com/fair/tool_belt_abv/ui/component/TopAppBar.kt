package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleAbvTopAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    onNavigationButtonClick: () -> Unit = { }
) {
    Column(modifier = modifier) {
        TopAppBar(
            colors = topAppBarColors(
                containerColor = colors.primaryContainer
            ),
            navigationIcon = {
                IconButton(onClick = { onNavigationButtonClick() }) {
                    Icon(
                        imageVector = Icons.Outlined.HelpOutline,
                        contentDescription = null
                    )
                }
            },
            title = { Text(text = title) }
        )
        ThemedDivider()
    }
}

@Preview
@Composable
fun SimpleAbvTopAppBarPreview() {
    SimpleAbvTopAppBar()
}
