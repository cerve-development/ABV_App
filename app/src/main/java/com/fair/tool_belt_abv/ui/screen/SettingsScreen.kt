package com.fair.tool_belt_abv.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.NewReleases
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.abv.shared.model.AppTheme
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.fair.tool_belt_abv.BuildConfig
import com.fair.tool_belt_abv.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
//    theme: AppTheme,
    isDarkMode: Boolean,
    modifier: Modifier = Modifier,
    onDarkModeChange: (Boolean) -> Unit = { },
    onAppThemeChange: (AppTheme) -> Unit = { },
    onFeatureRequestClick: () -> Unit = { },
    onBugReportClick: () -> Unit = { }
) {

    var openAppThemeDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(
                            id = R.string.NAV_DESTINATION_settings
                        )
                    )
                }
            )
        },
        contentWindowInsets = WindowInsets(0)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(sizes.small),
//            contentPadding = PaddingValues(sizes.medium)
        ) {
            item {
                ListItem(
                    modifier = Modifier.clickable { onDarkModeChange(!isDarkMode) },
                    leadingContent = { Icon(imageVector = Icons.Default.DarkMode, contentDescription = null) },
                    headlineContent = { Text(text = stringResource(id = R.string.LABEL_THEME_darkmode)) },
                    supportingContent = { Text(text = stringResource(id = R.string.SUBLABEL_THEME_darkmode)) },
                    trailingContent = {
                        Switch(
                            checked = isDarkMode,
                            thumbContent = if (isDarkMode) {
                                {
                                    Icon(
                                        imageVector = Icons.Filled.Check,
                                        contentDescription = null,
                                        modifier = Modifier.size(SwitchDefaults.IconSize)
                                    )
                                }
                            } else { null },
                            onCheckedChange = { change ->
                                onDarkModeChange(change)
                            }
                        )
                    }
                )

            }

            item {
                ListItem(
                    modifier = Modifier.clickable { openAppThemeDialog = true },
                    leadingContent = { Icon(imageVector = Icons.Default.Palette, contentDescription = null) },
                    headlineContent = { Text(text = stringResource(id = R.string.LABEL_THEME_theme)) },
                    supportingContent = { Text(text = stringResource(id = R.string.SUBLABEL_THEME_theme)) },
                    trailingContent = {
                        Icon(
                            imageVector = Icons.Filled.Circle,
                            contentDescription = null,
                            tint = colors.primary
                        )
                    }
                )
            }

            item {
                ListItem(
                    modifier = Modifier.clickable { onFeatureRequestClick() },
                    leadingContent = { Icon(imageVector = Icons.Default.NewReleases, contentDescription = null) },
                    headlineContent = { Text(text = stringResource(id = R.string.LABEL_SUPPORT_feature)) },
                    supportingContent = { Text(text = stringResource(id = R.string.SUBLABEL_SUPPORT_feature)) }
                )
            }

            item {
                ListItem(
                    modifier = Modifier.clickable { onBugReportClick() },
                    leadingContent = { Icon(imageVector = Icons.Default.BugReport, contentDescription = null) },
                    headlineContent = { Text(text = stringResource(id = R.string.LABEL_SUPPORT_bug)) },
                    supportingContent = { Text(text = stringResource(id = R.string.SUBLABEL_SUPPORT_bug)) }
                )
            }

            item {
                ListItem(
                    leadingContent = { Icon(imageVector = Icons.Default.Info, contentDescription = null) },
                    headlineContent = { Text(text = stringResource(id = R.string.LABEL_SYSTEM_version)) },
                    supportingContent = { Text(text = BuildConfig.VERSION_NAME) }
                )
            }


        }
    }

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
