package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CerveTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    showDivider: Boolean = false,
    navigationIcon: @Composable () -> Unit = { },
    actions: @Composable RowScope.() -> Unit = { }
) {
    Box(modifier = modifier, contentAlignment = Alignment.BottomCenter) {
        TopAppBar(
            colors = topAppBarColors(containerColor = Color.Transparent),
            navigationIcon = navigationIcon,
            actions = actions,
            title = { Text(text = title) }
        )
        if (showDivider) { ThemedDivider() }
    }
}

@Preview(showBackground = true)
@Composable
fun SimpleAbvTopAppBarPreview() {
    CerveTopAppBar("Hello world")
}
