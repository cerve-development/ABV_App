package com.fair.tool_belt_abv.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.twotone.BugReport
import androidx.compose.material.icons.twotone.DarkMode
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material.icons.twotone.NewReleases
import androidx.compose.material.icons.twotone.Palette
import androidx.compose.material.icons.twotone.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import com.fair.tool_belt_abv.ui.component.CerveListItem
import com.fair.tool_belt_abv.ui.component.CerveTopAppBar
import com.fair.tool_belt_abv.ui.component.ThemedAppThemeDialog
import com.fair.tool_belt_abv.ui.component.ThemedDivider

@Composable
fun SettingScreen(
    theme: AppTheme,
    isDarkMode: Boolean,
    modifier: Modifier = Modifier,
    onDarkModeChange: (Boolean) -> Unit = { },
    onAppThemeChange: (AppTheme) -> Unit = { },
    onFeatureRequestClick: () -> Unit = { },
    onBugReportClick: () -> Unit = { },
    onShareAppClick: () -> Unit = { }
) {

    var openAppThemeDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            CerveTopAppBar(title = stringResource(id = R.string.NAV_DESTINATION_settings))
        },
        contentWindowInsets = WindowInsets(0)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(sizes.small),
        ) {
            item {
                CerveListItem(
                    leadingIcon = Icons.TwoTone.DarkMode,
                    headlineText = stringResource(id = R.string.LABEL_THEME_darkmode),
                    supportingText = stringResource(id = R.string.SUBLABEL_THEME_theme),
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
                CerveListItem(
                    leadingIcon = Icons.TwoTone.Palette,
                    headlineText = stringResource(id = R.string.LABEL_THEME_theme),
                    supportingText =stringResource(id = R.string.SUBLABEL_THEME_theme),
                    trailingContent = {
                        Icon(
                            imageVector = Icons.Filled.Circle,
                            contentDescription = null,
                            tint = colors.primary
                        )
                    }
                )
                ThemedDivider()
            }

            item {
                CerveListItem(
                    leadingIcon = Icons.TwoTone.NewReleases,
                    headlineText = stringResource(id = R.string.LABEL_SUPPORT_feature),
                    supportingText = stringResource(id = R.string.SUBLABEL_SUPPORT_feature)
                ) { onFeatureRequestClick() }
            }

            item {
                CerveListItem(
                    leadingIcon = Icons.TwoTone.BugReport,
                    headlineText = stringResource(id = R.string.LABEL_SUPPORT_bug),
                    supportingText = stringResource(id = R.string.SUBLABEL_SUPPORT_bug)
                ) { onBugReportClick() }
            }

            item {
                CerveListItem(
                    leadingIcon = Icons.TwoTone.Share,
                    headlineText = stringResource(id = R.string.LABEL_SHARE_APP),
                    supportingText = stringResource(id = R.string.SUBLABEL_SHARE_APP)
                ) { onShareAppClick() }
                ThemedDivider()
            }

            item {
                CerveListItem(
                    leadingIcon = Icons.TwoTone.Info,
                    headlineText = stringResource(id = R.string.LABEL_SYSTEM_version),
                    supportingText = BuildConfig.VERSION_NAME
                )
            }


        }
    }

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
        theme = AppTheme.HOPS
    )
}
