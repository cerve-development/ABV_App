package com.fair.tool_belt_abv.ui.component

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes

@Composable
fun ThemedDivider(modifier: Modifier = Modifier) {

    Divider(
        modifier = modifier,
        thickness = sizes.xSmall / 2,
        color = colors.onSurface
    )

}