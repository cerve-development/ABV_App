package com.fair.tool_belt_abv.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fair.tool_belt_abv.model.AppState
import com.fair.tool_belt_abv.ui.navigation.LowerLevelDestinationGraph.Companion.asArgs
import com.fair.tool_belt_abv.ui.navigation.LowerLevelDestinationGraph.Companion.stringArguments
import com.fair.tool_belt_abv.ui.navigation.LowerLevelDestinationGraph.Companion.toArgs
import com.fair.tool_belt_abv.ui.screen.AbvCalculatorScreen
import com.fair.tool_belt_abv.ui.screen.ConverterScreen
import com.fair.tool_belt_abv.ui.screen.EquationCreationScreen
import com.fair.tool_belt_abv.ui.screen.Screen
import com.fair.tool_belt_abv.ui.viewmodel.AbvCalculatorViewModel
import com.fair.tool_belt_abv.ui.viewmodel.ConverterViewModel
import com.fair.tool_belt_abv.ui.viewmodel.EquationCreationViewModel
import com.fair.tool_belt_abv.ui.viewmodel.SettingViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavigationGraph(
    preferences: AppState,
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        startDestination = startDestination,
        navController = navController
    ) {
        composable(TopLevelDestinationGraph.CALCULATOR.route) {

            val vm : AbvCalculatorViewModel = getViewModel()
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

        composable(TopLevelDestinationGraph.CONVERTER.route) {
            val vm : ConverterViewModel = koinViewModel()
            val state by vm.result.collectAsStateWithLifecycle()

            ConverterScreen(
                gravityText = state.gravity,
                platoText = state.plato,
                brixText = state.brix,
                onValueChange = vm::updateValue
            )
        }

        composable(TopLevelDestinationGraph.SETTINGS.route) {
            val vm: SettingViewModel = koinViewModel()
            val context = LocalContext.current

//            SettingScreen(
////                unit = preferences.abvUnit,
////                equation = preferences.abvEquation,
//                theme = preferences.colorSchemePalette,
//                isDarkMode = preferences.inDarkMode ?: isSystemInDarkTheme(),
//                onUnitChange = vm::updateUnit,
//                onEquationChange = vm::updateEquation,
//                onAppThemeChange = vm::updateAppTheme,
//                onDarkModeChange = vm::updateDarkModeValue,
//                onFeatureRequestClick = {
//                    context.sendEmail(subject = EMAIL_SUBJECT_FEATURE)
//                },
//                onBugReportClick = {
//                    context.sendEmail(subject = EMAIL_SUBJECT_BUG)
//                }
//            )
        }

        composable(
            route = LowerLevelDestinationGraph.EQUATION.asArgs(),
            arguments = LowerLevelDestinationGraph.EQUATION.stringArguments()
        ) {
            val name = it.arguments?.getString(LowerLevelDestinationGraph.EQUATION.args)
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
                        onEquationDelete = if (name != null) {
                            { vm.deleteEquation(name) }
                        } else null,
                        onEquationSave = vm::saveEquation
                    ) { navController.popBackStack() }
                }

            }

        }

    }
}
