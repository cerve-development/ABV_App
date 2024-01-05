package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.cerve.co.material3extension.designsystem.ExtendedTheme.typography

@Composable
fun SurfaceButton(
    text: String,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = CircleShape,
    enabled: Boolean = true,
    onClick: () -> Unit
) {

    var multiplier by remember { mutableFloatStateOf(1f) }

    Surface(
        modifier = modifier,
        shape = shape,
        color = colors.primaryContainer,
        enabled = enabled,
        onClick = onClick
    ) {

        Text(
            modifier = Modifier
                .padding(sizes.medium)
                .wrapContentHeight(),
            text = text,
            textAlign = TextAlign.Center,
            style = typography.labelLarge.copy(
                fontSize = typography.labelLarge.fontSize * multiplier
            ),
            maxLines = 1,
            onTextLayout = {
                if (it.hasVisualOverflow) {
                    multiplier *= 0.99f
                }
            }
        )

    }

}