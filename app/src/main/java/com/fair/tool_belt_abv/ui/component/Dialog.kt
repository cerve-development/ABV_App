package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import com.cerve.co.material3extension.designsystem.ExtendedTheme.alphas
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.model.AppTheme

//@Composable
//fun ThemedAbvUnitDialog(
//    unit: AbvUnit,
//    isOpen: Boolean,
//    onOkClick: (AbvUnit) -> Unit,
//    onDismiss: () -> Unit
//) {
//    var selected by remember(unit) {
//        mutableStateOf(unit)
//    }
//
//    if (isOpen) {
//        AlertDialog(
//            onDismissRequest = { onDismiss() },
//            title = {
//                Text(
//                    modifier = Modifier.fillMaxWidth(),
//                    text = stringResource(id = R.string.LABEL_ABV_unit)
//                )
//            },
//            text = {
//                Column {
//                    AbvUnit.values().forEach { unit ->
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .clickable { selected = unit },
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.spacedBy(sizes.small)
//                        ) {
//                            RadioButton(
//                                selected = unit == selected,
//                                onClick = { selected = unit }
//                            )
//
//                            Text(text = stringResource(id = unit.textId))
//                        }
//                    }
//                }
//            },
//            confirmButton = {
//                TextButton(
//                    onClick = {
//                        onOkClick(selected)
//                        onDismiss()
//                    }
//                ) {
//                    Text(text = stringResource(id = R.string.DEFAULT_BUTTON_TEXT_ok))
//                }
//            },
//            dismissButton = {
//                TextButton(onClick = { onDismiss() }) {
//                    Text(text = stringResource(id = R.string.DEFAULT_BUTTON_TEXT_cancel))
//                }
//            }
//        )
//    }
//}
//
//@Composable
//fun ThemedAbvEquationDialog(
//    equation: AbvEquation,
//    isOpen: Boolean,
//    onOkClick: (AbvEquation) -> Unit,
//    onDismiss: () -> Unit
//) {
//    var selected by remember(equation) {
//        mutableStateOf(equation)
//    }
//
//    if (isOpen) {
//        AlertDialog(
//            onDismissRequest = { onDismiss() },
//            title = {
//                Text(
//                    modifier = Modifier.fillMaxWidth(),
//                    text = stringResource(id = R.string.LABEL_ABV_equation)
//                )
//            },
//            text = {
//                Column {
//                    AbvEquation.values().forEach { equation ->
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .clickable { selected = equation },
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.spacedBy(sizes.small)
//                        ) {
//                            RadioButton(
//                                selected = equation == selected,
//                                onClick = { selected = equation }
//                            )
//
//                            Text(text = stringResource(id = equation.textId))
//                        }
//                    }
//                }
//            },
//            confirmButton = {
//                TextButton(
//                    onClick = {
//                        onOkClick(selected)
//                        onDismiss()
//                    }
//                ) {
//                    Text(text = stringResource(id = R.string.DEFAULT_BUTTON_TEXT_ok))
//                }
//            },
//            dismissButton = {
//                TextButton(onClick = { onDismiss() }) {
//                    Text(text = stringResource(id = R.string.DEFAULT_BUTTON_TEXT_cancel))
//                }
//            }
//        )
//    }
//}

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
                    AppTheme.values().forEach { appTheme ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(sizes.small)
                        ) {
                            Surface(
                                onClick = { selected = appTheme },
                                modifier = Modifier.size(sizes.xLarge),
                                shape = CircleShape,
                                border = if (selected == appTheme) {
                                    themedBorder(
                                        thickness = sizes.xSmall,
                                        color = appTheme.color.copy(alpha = alphas.m_50)
                                    )
                                } else {
                                    null
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Circle,
                                    contentDescription = null,
                                    tint = appTheme.color
                                )
                            }

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
