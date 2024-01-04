package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.abv.shared.model.AppTheme
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.fair.tool_belt_abv.R

@Composable
fun ThemedAppThemeDialog(
    theme: AppTheme,
    isOpen: Boolean,
    onOkClick: (AppTheme) -> Unit,
    onDismiss: () -> Unit
) {
    var selected by remember(theme) {
        mutableStateOf(theme)
    }

    if (isOpen) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.LABEL_THEME_theme)
                )
            },
            text = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    AppTheme.entries.forEach { appTheme ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(sizes.small)
                        ) {
//                            Surface(
//                                onClick = { selected = appTheme },
//                                modifier = Modifier.size(sizes.xLarge),
//                                shape = CircleShape,
//                                border = if (selected == appTheme) {
//                                    themedBorder(
//                                        thickness = sizes.xSmall,
//                                        color = appTheme.color.copy(alpha = alphas.m_50)
//                                    )
//                                } else {
//                                    null
//                                }
//                            ) {
//                                Icon(
//                                    imageVector = Icons.Default.Circle,
//                                    contentDescription = null,
//                                    tint = appTheme.color
//                                )
//                            }

                            Text(text = appTheme.name)
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onOkClick(selected)
                        onDismiss()
                    }
                ) {
                    Text(text = stringResource(id = R.string.DEFAULT_BUTTON_TEXT_ok))
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = stringResource(id = R.string.DEFAULT_BUTTON_TEXT_cancel))
                }
            }
        )
    }
}

@Preview
@Composable
fun ThemedAppThemeDialogPreview() {
    ThemedAppThemeDialog(
        theme = AppTheme.LEGACY,
        isOpen = true,
        onOkClick = {
        },
        onDismiss = {
        }
    )
}
