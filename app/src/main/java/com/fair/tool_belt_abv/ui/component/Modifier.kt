package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import com.cerve.co.material3extension.designsystem.ExtendedTheme.shapes

fun Modifier.listItem(onClick: () -> Unit) : Modifier = composed {
    this.clip(shape = shapes.extraSmall)
        .clickable { onClick() }
}