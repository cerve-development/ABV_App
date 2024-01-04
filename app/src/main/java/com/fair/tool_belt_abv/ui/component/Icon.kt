package com.fair.tool_belt_abv.ui.component

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CerveIcon(
    imageVector: ImageVector,
    contentDescription: String? = null
) = Icon(imageVector = imageVector, contentDescription = contentDescription)