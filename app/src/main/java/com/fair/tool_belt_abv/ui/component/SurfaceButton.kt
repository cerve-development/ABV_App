package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun SurfaceButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {

    Surface(
        modifier = modifier,
        shape = CircleShape,
        enabled = enabled,
        onClick = onClick
    ) {

        Text(
            modifier = Modifier
                .padding(ButtonDefaults.TextButtonContentPadding)
                .wrapContentHeight(),
            text = text,
            textAlign = TextAlign.Center
        )

    }

}