package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.abv.shared.model.AppTheme
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.ui.viewmodel.SettingViewModel.SettingsState.Companion.primaryColors

@Composable
fun ThemedAppThemeDialog(
    theme: AppTheme,
    isOpen: Boolean,
    onOkClick: (AppTheme) -> Unit,
    onDismiss: () -> Unit
) {

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
                    primaryColors().forEach { selected ->
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clip(CircleShape)
                                .clickable {
                                    onOkClick(selected.theme)
                                    onDismiss()
                                }
                                .padding(sizes.small),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(sizes.small)
                        ) {
                            if (selected.theme == theme){
                                CerveIcon(
                                    modifier = Modifier.size(sizes.large),
                                    imageVector = Icons.Filled.CheckCircle,
                                    tint = selected.color
                                )
                            } else {
                                CerveIcon(
                                    modifier = Modifier.size(sizes.large),
                                    imageVector = Icons.Default.Circle,
                                    tint = selected.color
                                )
                            }

                            Text(
                                text = selected.theme.name,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            },
            confirmButton = { },
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
