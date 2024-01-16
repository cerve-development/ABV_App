package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.cerve.co.material3extension.designsystem.ExtendedTheme.typography

@Composable
fun SurfaceButton(
    text: String,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = CircleShape,
    containerColor: Color = colors.surface,
    enabled: Boolean = true,
    onClick: () -> Unit
) {

    Surface(
        modifier = modifier,
        shape = shape,
        color = containerColor,
        enabled = enabled,
        onClick = onClick
    ) {
        CerveScalingText(
            modifier = Modifier
                .padding(sizes.medium)
                .wrapContentHeight(),
            text = text,
            style = typography.bodyLarge
        )
    }
}
