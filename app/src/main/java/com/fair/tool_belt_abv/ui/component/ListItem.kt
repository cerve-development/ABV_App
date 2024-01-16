package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CerveListItem(
    leadingIcon: ImageVector,
    headlineText: String,
    supportingText: String,
    modifier: Modifier = Modifier,
    trailingContent: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    androidx.compose.material3.ListItem(
        modifier = modifier.shouldClick(onClick),
        leadingContent = { CerveIcon(imageVector = leadingIcon) },
        headlineContent = {
            Text(text = headlineText)
        },
        supportingContent = {
            Text(text = supportingText)
        },
        trailingContent = trailingContent
    )
}

fun Modifier.shouldClick(function: (() -> Unit)? = null) : Modifier {

    return function?.let {
        this then Modifier.clickable { function() }
    } ?: this

}