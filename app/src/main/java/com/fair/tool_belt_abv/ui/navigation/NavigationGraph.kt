package com.fair.tool_belt_abv.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.fair.tool_belt_abv.CalculatorViewModel
import com.fair.tool_belt_abv.ConverterViewModel
import com.fair.tool_belt_abv.ui.screen.CalculatorScreen
import com.fair.tool_belt_abv.ui.screen.ConverterScreen

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
) {

    NavHost(
        modifier = modifier.padding(sizes.small),
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

    }

}