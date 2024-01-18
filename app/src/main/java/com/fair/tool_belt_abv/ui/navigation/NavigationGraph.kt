package com.fair.tool_belt_abv.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.fair.tool_belt_abv.ui.component.cerveNavigationComposable
import com.fair.tool_belt_abv.ui.navigation.LowerLevelDestinationGraph.Companion.asArgs
import com.fair.tool_belt_abv.ui.navigation.LowerLevelDestinationGraph.Companion.stringArguments
import com.fair.tool_belt_abv.ui.navigation.LowerLevelDestinationGraph.EQUATION
import com.fair.tool_belt_abv.ui.screen.EquationCreationScreen
import com.fair.tool_belt_abv.ui.screen.MainNavGraph
import com.fair.tool_belt_abv.ui.screen.Screen
import com.fair.tool_belt_abv.ui.viewmodel.EquationCreationViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun RootNavGraph(
    modifier: Modifier = Modifier,
    startDestination: String = RootDestinationGraph.TOP_LEVEL_ROUTE.name,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        startDestination = startDestination,
        navController = navController
    ) {

        cerveNavigationComposable(
            route = RootDestinationGraph.TOP_LEVEL_ROUTE.name
        ) { MainNavGraph(rootNavController = navController) }

        cerveNavigationComposable(
            route = EQUATION.asArgs(),
            arguments = EQUATION.stringArguments()
        ) {
            val name = it.arguments?.getString(EQUATION.args)
            val vm: EquationCreationViewModel = koinViewModel(parameters = { parametersOf(name) })
            val uiState by vm.uiState.collectAsStateWithLifecycle()

            uiState.StateWrapper {
                LaunchedWrapper { event ->
                    when (event) {
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
