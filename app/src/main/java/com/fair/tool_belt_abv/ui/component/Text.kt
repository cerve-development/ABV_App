package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CerveScalingText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.headlineLarge
) {

    var multiplier by remember(text.length) { mutableFloatStateOf(1f) }

    Text(
        modifier = modifier.wrapContentHeight(),
        text = text,
        textAlign = TextAlign.Center,
        style = style.copy(
            fontSize = style.fontSize * multiplier
        ),
        softWrap = true,
        maxLines = 1,
        onTextLayout = {
            if (it.hasVisualOverflow) {
                multiplier *= 0.99f
            }
        }
    )
}