package com.fair.tool_belt_abv.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.abv.shared.domain.NewEquationUseCase
import com.cerve.abv.shared.model.AbvTestEquation
import com.cerve.co.material3extension.designsystem.ExtendedTheme
import com.cerve.co.material3extension.designsystem.rounded
import com.fair.tool_belt_abv.ui.component.CustomKeyboard

private const val EMPTY_STRING = ""

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EquationCreationScreen(
    state: NewEquationUseCase.State,
    modifier: Modifier = Modifier,
    onEquationUpdate: (String) -> Unit = { },
    onBackClick: () -> Unit = { }
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = { Text(text = "New Equation") }
            )
        },
        bottomBar = {
            CustomKeyboard(
                modifier = Modifier
                    .fillMaxHeight(0.65f)
                    .windowInsetsPadding(BottomAppBarDefaults.windowInsets),
                onClear = { onEquationUpdate(EMPTY_STRING) },
                onRemoveLast = {
                    val equation = when(state.equation.takeLast(2)) {
                        AbvTestEquation.StaticValues.OG.name -> { state.equation.dropLast(2) }
                        AbvTestEquation.StaticValues.FG.name -> { state.equation.dropLast(2) }
                        else -> state.equation.dropLast(1)
                    }
                    onEquationUpdate(equation)
                }
            ) { keyValue -> onEquationUpdate(state.equation + keyValue) }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(ExtendedTheme.sizes.medium)
        ) {
            Text(text = "sample")
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
                    .align(Alignment.CenterHorizontally),
                text = state.equation,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }

}

@Preview
@Preview(device = Devices.PIXEL_XL)
@Preview(device = Devices.PIXEL_3A)
@Composable
fun EquationCreationScreenPreview() {
    EquationCreationScreen(state = NewEquationUseCase.State())
}