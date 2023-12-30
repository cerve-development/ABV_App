package com.fair.tool_belt_abv.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.fair.tool_belt_abv.model.AbvUnit
import com.fair.tool_belt_abv.model.AppState
import com.fair.tool_belt_abv.ui.component.TabIndicator
import com.fair.tool_belt_abv.ui.screen.CalculatorScreen
import com.fair.tool_belt_abv.ui.screen.ConverterScreen
import com.fair.tool_belt_abv.ui.screen.EquationCreationScreen
import com.fair.tool_belt_abv.ui.screen.EquationListScreen
import com.fair.tool_belt_abv.ui.screen.SettingScreen
import com.fair.tool_belt_abv.ui.viewmodel.CalculatorViewModel
import com.fair.tool_belt_abv.ui.viewmodel.ConverterViewModel
import com.fair.tool_belt_abv.ui.viewmodel.EquationCreationViewModel
import com.fair.tool_belt_abv.ui.viewmodel.SettingViewModel
import com.fair.tool_belt_abv.util.EMAIL_SUBJECT_BUG
import com.fair.tool_belt_abv.util.EMAIL_SUBJECT_FEATURE
import com.fair.tool_belt_abv.util.sendEmail
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
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
            val state by calculatorVm.state.collectAsStateWithLifecycle()

            val pagerState = rememberPagerState(
                CalculatorDestinationGraph.Equation.ordinal
            ) { CalculatorDestinationGraph.entries.size }

            val position by remember { derivedStateOf { pagerState.currentPage } }

            val scope = rememberCoroutineScope()

            Scaffold(
                topBar = {
                    Column {
                        ScrollableTabRow(
//                        modifier = Modifier.fillMaxWidth(),
                            selectedTabIndex = position,
                            indicator = { tabPositions ->
                                if (position < tabPositions.size) {
                                    TabIndicator(tabPositions[position])
                                }
                            },
                            divider = { },
                            edgePadding = sizes.medium
                        ) {
                            CalculatorDestinationGraph.entries.forEachIndexed { index, destination ->
                                Tab(
                                    selected = position == index,
                                    onClick = {
                                        scope.launch {
                                            pagerState.animateScrollToPage(index)
                                        }
                                    }
                                ) {
                                    Text(
                                        modifier = Modifier.padding(sizes.medium),
                                        text = stringResource(destination.nameId)
                                    )
                                }

                            }

                        }
                        Divider()
                    }
                },
            ) { padding ->
                state?.let { state ->
                HorizontalPager(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize(),
                    state = pagerState,
                ) {
                    when(position) {
                        CalculatorDestinationGraph.Result.ordinal -> {

                                CalculatorScreen(
                                    originalText = state.original,
                                    finalText = state.final,
                                    abvUnit = AbvUnit.SG,//state.unit,
//                                    abvEquation = state.equation,
                                    abvValue = state.abv,
                                    attenuationValue = state.attenuation,
                                    errorMessage = null,//state.warning,
                                    onUnitSelect = calculatorVm::updateUnit,
                                    onEquationSelect = calculatorVm::updateCalculatorEquation,
                                    onOriginalTextChange = calculatorVm::updateOriginalValue,
                                    onFinalTextChange = calculatorVm::updateFinalValue
                                )

                        }
                        CalculatorDestinationGraph.Equation.ordinal -> {
                            EquationListScreen(
                                state.equations,
                                modifier = Modifier.padding(sizes.medium),
                                onCreateEquation = {
                                    navController.navigate(LowerLevelDestinationGraph.EQUATION.route)
                                }
                            )
                        }
                    }  }
                }

            }

        }

        composable(TopLevelDestinationGraph.CONVERTER.route) {
            val state by converterVm.result.collectAsStateWithLifecycle()

            ConverterScreen(
                gravityText = state.gravity,
                platoText = state.plato,
                brixText = state.brix,
                onValueChange = converterVm::updateValue
            )
        }

        composable(TopLevelDestinationGraph.SETTINGS.route) {
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

        composable(LowerLevelDestinationGraph.EQUATION.route) {
            val vm : EquationCreationViewModel = koinViewModel()
            val state by vm.uiState.collectAsStateWithLifecycle()

            EquationCreationScreen(
                state = state,
                onEquationUpdate = vm::updateEquation
            ) { navController.popBackStack() }
        }

    }
}
