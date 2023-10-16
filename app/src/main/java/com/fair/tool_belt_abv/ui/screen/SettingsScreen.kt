package com.fair.tool_belt_abv.ui.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fair.tool_belt_abv.model.AbvEquation
import com.fair.tool_belt_abv.model.AbvUnit
import com.fair.tool_belt_abv.model.AppTheme
import com.fair.tool_belt_abv.ui.component.ThemedAbvEquationDialog
import com.fair.tool_belt_abv.ui.component.ThemedAbvUnitDialog
import com.fair.tool_belt_abv.ui.component.ThemedAppThemeDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    unit: AbvUnit,
    equation: AbvEquation,
    theme: AppTheme,
    isDarkMode: Boolean,
    modifier: Modifier = Modifier,
    onUnitChange: (AbvUnit) -> Unit = { },
    onEquationChange: (AbvEquation) -> Unit = { },
    onDarkModeChange: (Boolean) -> Unit = { },
    onAppThemeChange: (AppTheme) -> Unit = { },
    onFeatureRequestClick: () -> Unit = { },
    onBugReportClick: () -> Unit = { }
) {
    var openAbvUnitDialog by remember { mutableStateOf(false) }
    var openAbvEquationDialog by remember { mutableStateOf(false) }
    var openAppThemeDialog by remember { mutableStateOf(false) }

    //TODO
//    LazyColumn(
//        modifier = modifier,
//        verticalArrangement = Arrangement.spacedBy(sizes.small),
//        contentPadding = PaddingValues(sizes.medium)
//    ) {
//        item {
//            ListItem(
//                modifier = Modifier.listItem {
//                    openAbvUnitDialog = true
//                },
//                leadingContent = {
//                    Icon(imageVector = Icons.Default.SquareFoot, contentDescription = null)
//                },
//                headlineText = {
//                    Text(text = stringResource(id = R.string.LABEL_ABV_unit))
//                },
//                supportingText = {
//                    Text(text = stringResource(id = R.string.SUBLABEL_ABV_unit))
//                }
//            )
//        }
//        item {
//            ListItem(
//                modifier = Modifier.listItem {
//                    openAbvEquationDialog = true
//                },
//                leadingContent = {
//                    Icon(imageVector = Icons.Default.Functions, contentDescription = null)
//                },
//                headlineText = {
//                    Text(text = stringResource(id = R.string.LABEL_ABV_equation))
//                },
//                supportingText = {
//                    Text(text = stringResource(id = R.string.SUBLABEL_ABV_equation))
//                }
//            )
//        }
//
//        item {
//            ListItem(
//                modifier = Modifier.listItem {
//                    onDarkModeChange(!isDarkMode)
//                },
//                leadingContent = {
//                    Icon(imageVector = Icons.Default.DarkMode, contentDescription = null)
//                },
//                headlineText = {
//                    Text(text = stringResource(id = R.string.LABEL_THEME_darkmode))
//                },
//                supportingText = {
//                    Text(text = stringResource(id = R.string.SUBLABEL_THEME_darkmode))
//                },
//                trailingContent = {
//                    Switch(
//                        checked = isDarkMode,
//                        thumbContent = if (isDarkMode) {
//                            {
//                                Icon(
//                                    imageVector = Icons.Filled.Check,
//                                    contentDescription = null,
//                                    modifier = Modifier.size(SwitchDefaults.IconSize)
//                                )
//                            }
//                        } else { null },
//                        onCheckedChange = { change ->
//                            onDarkModeChange(change)
//                        }
//                    )
//                }
//            )
//        }
//        item {
//            ListItem(
//                modifier = Modifier.listItem {
//                    openAppThemeDialog = true
//                },
//                leadingContent = {
//                    Icon(imageVector = Icons.Default.Palette, contentDescription = null)
//                },
//                headlineText = {
//                    Text(text = stringResource(id = R.string.LABEL_THEME_theme))
//                },
//                supportingText = {
//                    Text(text = stringResource(id = R.string.SUBLABEL_THEME_theme))
//                },
//                trailingContent = {
//                    Icon(
//                        imageVector = Icons.Filled.Circle,
//                        contentDescription = null,
//                        tint = colors.primary
//                    )
//                }
//            )
//        }
//
//        item {
//            ListItem(
//                modifier = Modifier.listItem {
//                    onFeatureRequestClick()
//                },
//                leadingContent = {
//                    Icon(imageVector = Icons.Default.NewReleases, contentDescription = null)
//                },
//                headlineText = {
//                    Text(text = stringResource(id = R.string.LABEL_SUPPORT_feature))
//                },
//                supportingText = {
//                    Text(text = stringResource(id = R.string.SUBLABEL_SUPPORT_feature))
//                }
//            )
//        }
//        item {
//            ListItem(
//                modifier = Modifier.listItem {
//                    onBugReportClick()
//                },
//                leadingContent = {
//                    Icon(imageVector = Icons.Default.BugReport, contentDescription = null)
//                },
//                headlineText = {
//                    Text(text = stringResource(id = R.string.LABEL_SUPPORT_bug))
//                },
//                supportingText = {
//                    Text(text = stringResource(id = R.string.SUBLABEL_SUPPORT_bug))
//                }
//            )
//        }
//
//        item {
//            ListItem(
//                modifier = Modifier.listItem { },
//                leadingContent = {
//                    Icon(imageVector = Icons.Default.Info, contentDescription = null)
//                },
//                headlineText = {
//                    Text(text = stringResource(id = R.string.LABEL_SYSTEM_version))
//                },
//                supportingText = {
//                    Text(text = BuildConfig.VERSION_NAME)
//                }
//            )
//        }
//    }

    ThemedAbvUnitDialog(
        unit = unit,
        isOpen = openAbvUnitDialog,
        onOkClick = { onUnitChange(it) },
        onDismiss = { openAbvUnitDialog = false }
    )

    ThemedAbvEquationDialog(
        equation = equation,
        isOpen = openAbvEquationDialog,
        onOkClick = { onEquationChange(it) },
        onDismiss = { openAbvEquationDialog = false }
    )

    ThemedAppThemeDialog(
        theme = theme,
        isOpen = openAppThemeDialog,
        onOkClick = { onAppThemeChange(it) },
        onDismiss = { openAppThemeDialog = false }
    )
}

@Preview
@Composable
fun SettingScreenPreview() {
    SettingScreen(
        isDarkMode = true,
        unit = AbvUnit.SG,
        equation = AbvEquation.S,
        theme = AppTheme.HOPS
    )
}
