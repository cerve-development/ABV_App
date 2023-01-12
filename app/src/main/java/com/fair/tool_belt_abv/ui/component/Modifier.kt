package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import com.cerve.co.material3extension.designsystem.ExtendedTheme
import com.cerve.co.material3extension.designsystem.ExtendedTheme.alphas
import com.cerve.co.material3extension.designsystem.ExtendedTheme.shapes
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes

fun Modifier.listItem(onClick: () -> Unit) : Modifier = composed {
    this
        .clip(shape = shapes.extraSmall)
        .border(
            width = sizes.xSmall / 2,
            color = ExtendedTheme.colors.onSurface.copy(alpha = alphas.medium_30),
            shape = shapes.extraSmall
        )
        .clickable { onClick() }
}