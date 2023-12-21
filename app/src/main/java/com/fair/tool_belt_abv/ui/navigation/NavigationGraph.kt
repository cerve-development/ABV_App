package com.fair.tool_belt_abv.ui.navigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fair.tool_belt_abv.model.AppState
import com.fair.tool_belt_abv.ui.screen.CalculatorScreen
import com.fair.tool_belt_abv.ui.screen.ConverterScreen
import com.fair.tool_belt_abv.ui.screen.SettingScreen
import com.fair.tool_belt_abv.ui.viewmodel.CalculatorViewModel
import com.fair.tool_belt_abv.ui.viewmodel.ConverterViewModel
import com.fair.tool_belt_abv.ui.viewmodel.SettingViewModel
import com.fair.tool_belt_abv.util.EMAIL_SUBJECT_BUG
import com.fair.tool_belt_abv.util.EMAIL_SUBJECT_FEATURE
import com.fair.tool_belt_abv.util.sendEmail
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationGraph(
    preferences: AppState,
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
    calculatorVm: CalculatorViewModel = koinViewModel(),
    converterVm: ConverterViewModel = koinViewModel(),
    settingVM: SettingViewModel = koinViewModel()
) {
    NavHost(
        modifier = modifier,
        startDestination = startDestination,
        navController = navController
    ) {
        composable(TopLevelDestinationGraph.CALCULATOR.route) {
            val calculatorState by calculatorVm.state.collectAsStateWithLifecycle()

            // TODO HANDLE NULL STATE
            calculatorState?.let { state ->
                CalculatorScreen(
                    originalText = state.original,
                    finalText = state.final,
                    abvUnit = state.unit,
                    abvEquation = state.equation,
                    abvValue = state.abv,
                    attenuationValue = state.attenuation,
                    errorMessage = state.errorMessage,
                    onUnitSelect = calculatorVm::updateUnit,
                    onEquationSelect = calculatorVm::updateEquation,
                    onOriginalTextChange = calculatorVm::updateOriginalValue,
                    onFinalTextChange = calculatorVm::updateFinalValue
                )
            }
        }

        composable(TopLevelDestinationGraph.CONVERTER.route) {
            val convertorState by converterVm.result.collectAsStateWithLifecycle()

            ConverterScreen(
                gravityText = convertorState.sg,
                platoText = convertorState.plato,
                brixText = convertorState.brix,
                onValueChange = converterVm::updateValue
            )
        }

        composable(TopLevelDestinationGraph.SETTINGS) {
            val context = LocalContext.current

            SettingScreen(
                unit = preferences.abvUnit,
                equation = preferences.abvEquation,
                theme = preferences.colorSchemePalette,
                isDarkMode = preferences.inDarkMode ?: isSystemInDarkTheme(),
                onUnitChange = settingVM::updateUnit,
                onEquationChange = settingVM::updateEquation,
                onAppThemeChange = settingVM::updateAppTheme,
                onDarkModeChange = settingVM::updateDarkModeValue,
                onFeatureRequestClick = {
                    context.sendEmail(subject = EMAIL_SUBJECT_FEATURE)
                },
                onBugReportClick = {
                    context.sendEmail(subject = EMAIL_SUBJECT_BUG)
                }
            )
        }
    }
}
