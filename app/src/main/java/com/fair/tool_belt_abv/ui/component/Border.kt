package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes

@Composable
fun themedBorder(
    width: Dp = sizes.xSmall / 2,
    color: Color = colors.onSurface
) = BorderStroke(width = width, color = color)
