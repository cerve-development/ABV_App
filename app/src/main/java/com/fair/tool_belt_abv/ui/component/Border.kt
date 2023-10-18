package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.DividerDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun themedBorder(
    color: Color = DividerDefaults.color,
    thickness: Dp = DividerDefaults.Thickness,
) = BorderStroke(width = thickness, color = color)
