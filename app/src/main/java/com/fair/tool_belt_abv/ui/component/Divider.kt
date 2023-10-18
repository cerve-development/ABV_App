package com.fair.tool_belt_abv.ui.component

import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors

@Composable
fun ThemedDivider(
    modifier: Modifier = Modifier,
    color: Color = colors.outline,
    thickness: Dp = DividerDefaults.Thickness
) { Divider(modifier = modifier, color = color, thickness = thickness) }
