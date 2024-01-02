package com.fair.tool_belt_abv.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SettingScreen(
//    unit: AbvUnit,
//    equation: AbvEquation,
//    theme: AppTheme,
//    isDarkMode: Boolean,
//    modifier: Modifier = Modifier,
//    onUnitChange: (AbvUnit) -> Unit = { },
//    onEquationChange: (AbvEquation) -> Unit = { },
//    onDarkModeChange: (Boolean) -> Unit = { },
//    onAppThemeChange: (AppTheme) -> Unit = { },
//    onFeatureRequestClick: () -> Unit = { },
//    onBugReportClick: () -> Unit = { }
) {
//    var openAbvUnitDialog by remember { mutableStateOf(false) }
//    var openAbvEquationDialog by remember { mutableStateOf(false) }
//    var openAppThemeDialog by remember { mutableStateOf(false) }
//
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
//                headlineContent = {
//                    Text(text = stringResource(id = R.string.LABEL_ABV_unit))
//                },
//                supportingContent = {
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
//                headlineContent = {
//                    Text(text = stringResource(id = R.string.LABEL_ABV_equation))
//                },
//                supportingContent = {
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
//                headlineContent = {
//                    Text(text = stringResource(id = R.string.LABEL_THEME_darkmode))
//                },
//                supportingContent = {
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
//                headlineContent = {
//                    Text(text = stringResource(id = R.string.LABEL_THEME_theme))
//                },
//                supportingContent = {
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
//                headlineContent = {
//                    Text(text = stringResource(id = R.string.LABEL_SUPPORT_feature))
//                },
//                supportingContent = {
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
//                headlineContent = {
//                    Text(text = stringResource(id = R.string.LABEL_SUPPORT_bug))
//                },
//                supportingContent = {
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
//                headlineContent = {
//                    Text(text = stringResource(id = R.string.LABEL_SYSTEM_version))
//                },
//                supportingContent = {
//                    Text(text = BuildConfig.VERSION_NAME)
//                }
//            )
//        }
//    }
//
//    ThemedAbvUnitDialog(
//        unit = unit,
//        isOpen = openAbvUnitDialog,
//        onOkClick = { onUnitChange(it) },
//        onDismiss = { openAbvUnitDialog = false }
//    )
//
//    ThemedAbvEquationDialog(
//        equation = equation,
//        isOpen = openAbvEquationDialog,
//        onOkClick = { onEquationChange(it) },
//        onDismiss = { openAbvEquationDialog = false }
//    )
//
//    ThemedAppThemeDialog(
//        theme = theme,
//        isOpen = openAppThemeDialog,
//        onOkClick = { onAppThemeChange(it) },
//        onDismiss = { openAppThemeDialog = false }
//    )
}

@Preview
@Composable
fun SettingScreenPreview() {
//    SettingScreen(
//        isDarkMode = true,
//        unit = AbvUnit.SG,
////        equation = AbvEquation.S,
//        theme = AppTheme.HOPS
//    )
}
