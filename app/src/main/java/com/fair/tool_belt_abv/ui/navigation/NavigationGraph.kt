package com.fair.tool_belt_abv.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.cerve.abv.shared.model.AppTheme
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.ui.component.cerveNavigationComposable
import com.fair.tool_belt_abv.ui.navigation.LowerLevelDestinationGraph.Companion.asArgs
import com.fair.tool_belt_abv.ui.navigation.LowerLevelDestinationGraph.Companion.stringArguments
import com.fair.tool_belt_abv.ui.navigation.LowerLevelDestinationGraph.Companion.toArgs
import com.fair.tool_belt_abv.ui.navigation.LowerLevelDestinationGraph.EQUATION
import com.fair.tool_belt_abv.ui.screen.AbvCalculatorScreen
import com.fair.tool_belt_abv.ui.screen.ConverterScreen
import com.fair.tool_belt_abv.ui.screen.EquationCreationScreen
import com.fair.tool_belt_abv.ui.screen.Screen
import com.fair.tool_belt_abv.ui.screen.SettingScreen
import com.fair.tool_belt_abv.ui.viewmodel.AbvCalculatorViewModel
import com.fair.tool_belt_abv.ui.viewmodel.ConverterViewModel
import com.fair.tool_belt_abv.ui.viewmodel.EquationCreationViewModel
import com.fair.tool_belt_abv.ui.viewmodel.SettingViewModel
import com.fair.tool_belt_abv.util.EMAIL_SUBJECT_BUG
import com.fair.tool_belt_abv.util.EMAIL_SUBJECT_FEATURE
import com.fair.tool_belt_abv.util.STORE_LINK_ANDROID
import com.fair.tool_belt_abv.util.getVersion
import com.fair.tool_belt_abv.util.sendEmail
import com.fair.tool_belt_abv.util.shared
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavigationGraph(
    inDarkMode: Boolean,
    appTheme: AppTheme,
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        startDestination = startDestination,
        navController = navController
    ) {
        cerveNavigationComposable(TopLevelDestinationGraph.CALCULATOR.route) {

            val vm : AbvCalculatorViewModel = koinViewModel()
            val uiState by vm.uiState.collectAsStateWithLifecycle()

            uiState.StateWrapper {
                ScreenWrapper { state ->
                    //TODO investigate why non null still showing
                    AbvCalculatorScreen(
                        state = state!!,
                        onUnitSelect = vm::updateUnit,
                        onEquationSelect = vm::updateEquation,
                        onOGValueUpdate = vm::updateOriginalValue,
                        onFGValueUpdate = vm::updateFinalValue
                    ) { args ->
                        val route = LowerLevelDestinationGraph.EQUATION.toArgs(args)
                        navController.navigate(route)
                    }
                }
            }

        }

        cerveNavigationComposable(TopLevelDestinationGraph.CONVERTER.route) {
            val vm : ConverterViewModel = koinViewModel()
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
            val context = LocalContext.current

            SettingScreen(
                inDarkMode = inDarkMode,
                appTheme = appTheme,
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

        cerveNavigationComposable(
            route = EQUATION.asArgs(),
            arguments = EQUATION.stringArguments()
        ) {
            val name = remember { it.arguments?.getString(EQUATION.args) }
            val vm : EquationCreationViewModel = koinViewModel(parameters = { parametersOf(name) })
            val uiState by vm.uiState.collectAsStateWithLifecycle()

            uiState.StateWrapper {

                LaunchedWrapper { event ->
                    when(event) {
                        is Screen.EventType.Navigation -> navController.popBackStack()
                        else -> Unit
                    }
                }

                ScreenWrapper { state ->
                    EquationCreationScreen(
                        state = state,
                        onNameUpdate = vm::updateName,
                        onEquationUpdate = vm::updateEquation,
                        onEquationDelete = vm::deleteEquation,
                        onEquationSave = vm::saveEquation
                    ) { navController.popBackStack() }
                }

            }

        }

    }
}
