package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.cerve.co.material3extension.designsystem.ExtendedTheme.shapes
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes

@Composable
fun ThemedChip(
    modifier: Modifier = Modifier,
    shape: Shape = shapes.medium,
    border: BorderStroke? = null,
    color: Color = colors.surfaceVariant,
    paddingValues: PaddingValues = PaddingValues(
        vertical = sizes.xSmall,
        horizontal = sizes.small
    ),
    content: @Composable (Modifier) -> Unit = { }
) {

    Surface(
        modifier = modifier,
        shape = shape,
        border = border,
        color = color,
    ) { content(Modifier.padding(paddingValues)) }

}