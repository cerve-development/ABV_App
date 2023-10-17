package com.fair.tool_belt_abv.ui.component

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors

@Composable
fun ThemedDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier,
        color = colors.onSurface
    )
}
