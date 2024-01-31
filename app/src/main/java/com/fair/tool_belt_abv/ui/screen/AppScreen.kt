package com.fair.tool_belt_abv.ui.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.ui.component.CerveScaffold
import com.fair.tool_belt_abv.ui.component.SimpleAbvNavigationBar
import com.fair.tool_belt_abv.ui.component.cerveNavigationComposable
import com.fair.tool_belt_abv.ui.navigation.LowerLevelDestinationGraph
import com.fair.tool_belt_abv.ui.navigation.LowerLevelDestinationGraph.Companion.toArgs
import com.fair.tool_belt_abv.ui.navigation.RootDestinationGraph
import com.fair.tool_belt_abv.ui.navigation.TopLevelDestinationGraph
import com.fair.tool_belt_abv.ui.viewmodel.AbvCalculatorViewModel
import com.fair.tool_belt_abv.ui.viewmodel.ConverterViewModel
import com.fair.tool_belt_abv.ui.viewmodel.SettingViewModel
import com.fair.tool_belt_abv.util.EMAIL_SUBJECT_BUG
import com.fair.tool_belt_abv.util.EMAIL_SUBJECT_FEATURE
import com.fair.tool_belt_abv.util.STORE_LINK_ANDROID
import com.fair.tool_belt_abv.util.getVersion
import com.fair.tool_belt_abv.util.sendEmail
import com.fair.tool_belt_abv.util.shared
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    rootNavController: NavHostController,
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    CerveScaffold(
        modifier = modifier,
        bottomBar = {
            SimpleAbvNavigationBar(
                currentDestination = backStackEntry?.destination?.route,
                onNavigateToDestination = { destination ->
                    navController.navigate(destination.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // re-selecting the same item
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    ) {
        NavHost(
            route = RootDestinationGraph.TOP_LEVEL_ROUTE.name,
            startDestination = TopLevelDestinationGraph.CALCULATOR.route,
            navController = navController
        ) {
            cerveNavigationComposable(TopLevelDestinationGraph.CALCULATOR.route) {
                val vm: AbvCalculatorViewModel = koinViewModel()
                val uiState by vm.uiState.collectAsStateWithLifecycle()

                uiState.StateWrapper {
                    ScreenWrapper { state ->
                        // TODO investigate why non null still showing
                        AbvCalculatorScreen(
                            state = state,
                            onUnitSelect = vm::updateUnit,
                            onEquationSelect = vm::updateEquation,
                            onOGValueUpdate = vm::updateOriginalValue,
                            onFGValueUpdate = vm::updateFinalValue
                        ) { args ->
                            val route = LowerLevelDestinationGraph.EQUATION.toArgs(args)
                            rootNavController.navigate(route)
                        }
                    }
                }
            }

            cerveNavigationComposable(TopLevelDestinationGraph.CONVERTER.route) {
                val vm: ConverterViewModel = koinViewModel()
                val state by vm.result.collectAsStateWithLifecycle()

                ConverterScreen(
                    gravityText = state.gravity,
                    platoText = state.plato,
                    brixText = state.brix,
                    onValueChange = vm::updateValue
                )
            }

            cerveNavigationComposable(TopLevelDestinationGraph.SETTINGS.route) {
                val vm: SettingViewModel = koinViewModel()
                val uiState by vm.uiState.collectAsStateWithLifecycle()

                val context = LocalContext.current

                uiState.StateWrapper {
                    ScreenWrapper { state ->
                        SettingScreen(
                            inDarkMode = state.inDarkMode ?: isSystemInDarkTheme(),
                            appTheme = state.colorSchemePalette,
                            appVersion = context.getVersion(),
                            onAppThemeChange = vm::updateAppTheme,
                            onDarkModeChange = vm::updateDarkModeValue,
                            onFeatureRequestClick = {
                                context.sendEmail(
                                    subject = EMAIL_SUBJECT_FEATURE,
                                    title = context.getString(R.string.LABEL_SUPPORT_feature)
                                )
                            },
                            onBugReportClick = {
                                context.sendEmail(
                                    subject = EMAIL_SUBJECT_BUG,
                                    title = context.getString(R.string.LABEL_SUPPORT_bug)
                                )
                            },
                            onShareAppClick = {
                                context.shared(
                                    title = context.getString(R.string.SUBLABEL_SHARE_APP),
                                    value = context.getString(
                                        R.string.SUBLABEL_SHARE_APP_MORE_INFO,
                                        STORE_LINK_ANDROID
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AppScreenPreview() {
//    AppScreen(true, AppTheme.LEGACY)
}
