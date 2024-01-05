package com.fair.tool_belt_abv.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.cerve.abv.shared.model.AbvEquation
import com.cerve.abv.shared.model.AbvUnit
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.cerve.co.material3extension.designsystem.rounded
import com.fair.tool_belt_abv.ui.component.TabIndicator
import com.fair.tool_belt_abv.ui.navigation.CalculatorDestinationGraph
import com.fair.tool_belt_abv.ui.viewmodel.AbvCalculatorViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AbvCalculatorScreen(
    state: AbvCalculatorViewModel.AbvCalculatorState,
    onUnitSelect: (AbvUnit) -> Unit = { },
    onEquationSelect: (AbvEquation) -> Unit = { },
    onOGValueUpdate: (String) -> Unit = { },
    onFGValueUpdate: (String) -> Unit = { },
    onEquationCreationNavigate: (String?) -> Unit = { }
) {
    val scope = rememberCoroutineScope()

    val pagerState = rememberPagerState(
        CalculatorDestinationGraph.Result.ordinal
    ) { CalculatorDestinationGraph.entries.size }

    val position = remember { pagerState.currentPage }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    ScrollableTabRow(
                        modifier = Modifier.fillMaxWidth(),
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
                                    text = stringResource(destination.nameId),
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }

                        }

                    }
                },
                actions = {
                    IconButton(onClick = {
                        onEquationCreationNavigate(null)
                    }) { Icon(rounded.EditNote, null) }
                }
            )
        },
        contentWindowInsets = WindowInsets(0)
    ) { padding ->

        HorizontalPager(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            state = pagerState,
        ) {
            when(position) {
                CalculatorDestinationGraph.Result.ordinal -> {
                    CalculatorScreen(
                        originalText = state.og,
                        finalText = state.fg,
                        abvUnit = state.selectedAbvUnit,
                        abvEquation = state.selectedEquation,
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