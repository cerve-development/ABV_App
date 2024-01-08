package com.fair.tool_belt_abv.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.cerve.abv.shared.model.AbvEquation
import com.cerve.abv.shared.model.AbvUnit
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.cerve.co.material3extension.designsystem.rounded
import com.fair.tool_belt_abv.ui.component.CerveScaffold
import com.fair.tool_belt_abv.ui.component.CerveTabTopAppBar
import com.fair.tool_belt_abv.ui.navigation.CalculatorDestinationGraph
import com.fair.tool_belt_abv.ui.viewmodel.AbvCalculatorViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AbvCalculatorScreen(
    state: AbvCalculatorViewModel.AbvCalculatorState,
    onUnitSelect: (AbvUnit) -> Unit = { },
    onEquationSelect: (AbvEquation) -> Unit = { },
    onOGValueUpdate: (String) -> Unit = { },
    onFGValueUpdate: (String) -> Unit = { },
    onEquationCreationNavigate: (String?) -> Unit = { }
) {

    val pagerState = rememberPagerState(
        CalculatorDestinationGraph.Result.ordinal
    ) { CalculatorDestinationGraph.entries.size }

    CerveScaffold(
        topBar = {
            CerveTabTopAppBar(
                state = pagerState,
                items = CalculatorDestinationGraph.entries,
                actions = {
                    IconButton(onClick = {
                        onEquationCreationNavigate(null)
                    }) { Icon(rounded.EditNote, null) }
                }
            ) { tab ->
                Text(
                    text = stringResource(tab.nameId),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
        ) { page ->
            when(page) {
                CalculatorDestinationGraph.Result.ordinal -> {
                    CalculatorScreen(
                        originalText = state.og,
                        finalText = state.fg,
                        abvUnit = state.selectedAbvUnit,
                        abvValue = state.abv,
                        attenuationValue = state.attenuation,
                        onUnitSelect = onUnitSelect,
                        onOriginalTextChange = onOGValueUpdate,
                        onFinalTextChange = onFGValueUpdate
                    )
                }
                CalculatorDestinationGraph.Equation.ordinal -> {
                    EquationListScreen(
                        modifier = Modifier.padding(sizes.medium),
                        selectedAbvEquation = state.selectedEquation,
                        abvEquationList = state.abvEquationList,
                        onEquationSelect = onEquationSelect,
                        onEquationUpdate = onEquationCreationNavigate
                    )
                }
            }
        }

    }
}