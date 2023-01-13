package com.fair.tool_belt_abv.ui.navigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fair.tool_belt_abv.CalculatorViewModel
import com.fair.tool_belt_abv.ConverterViewModel
import com.fair.tool_belt_abv.SettingViewModel
import com.fair.tool_belt_abv.model.AppPreferences
import com.fair.tool_belt_abv.ui.screen.CalculatorScreen
import com.fair.tool_belt_abv.ui.screen.ConverterScreen
import com.fair.tool_belt_abv.ui.screen.SettingScreen
import com.fair.tool_belt_abv.util.EMAIL_SUBJECT_BUG
import com.fair.tool_belt_abv.util.EMAIL_SUBJECT_FEATURE
import com.fair.tool_belt_abv.util.sendEmail

@Composable
fun NavigationGraph(
    preferences: AppPreferences,
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
) {

    NavHost(
        modifier = modifier,
        startDestination = startDestination,
        navController = navController
    ) {

        composable(TopLevelDestinationGraph.CALCULATOR.route) {
            val calculatorVm = hiltViewModel<CalculatorViewModel>()
            val calculatorState by calculatorVm.state.collectAsStateWithLifecycle()

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
            val converterVm = hiltViewModel<ConverterViewModel>()
            val convertorState by converterVm.result.collectAsStateWithLifecycle()

            ConverterScreen(
                gravityText = convertorState.sg,
                platoText = convertorState.plato,
                brixText = convertorState.brix,
                onValueChange = converterVm::updateValue
            )
        }

        composable(TopLevelDestinationGraph.SETTINGS) {
            val settingVM = hiltViewModel<SettingViewModel>()
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