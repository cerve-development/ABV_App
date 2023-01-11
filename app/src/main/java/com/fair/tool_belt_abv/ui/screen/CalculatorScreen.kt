package com.fair.tool_belt_abv.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.model.AbvEquation
import com.fair.tool_belt_abv.model.AbvUnit
import com.fair.tool_belt_abv.ui.component.DefaultTextField
import com.fair.tool_belt_abv.ui.component.RadioGroupEquation
import com.fair.tool_belt_abv.ui.component.RadioGroupUnit

@Composable
fun CalculatorScreen(
    abvUnit: AbvUnit,
    abvEquation: AbvEquation,
    modifier: Modifier = Modifier,
) {

    var originalText by remember { mutableStateOf("") }
    var finalText by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(sizes.small),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(sizes.small),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DefaultTextField(
                value = originalText,
                onValueChange = { change ->
                    originalText = change
                },
                label = { Text(text = stringResource(id = R.string.o_g)) },
            )

            DefaultTextField(
                value = finalText,
                onValueChange = { change ->
                    finalText = change
                },
                imeAction = ImeAction.Done,
                label = { Text(text = stringResource(id = R.string.f_g)) },
            )
        }

        RadioGroupUnit(
            selected = abvUnit,
            label = stringResource(id = R.string.LABEL_ABV_unit)
        )

        RadioGroupEquation(
            selected = abvEquation,
            label = stringResource(id = R.string.LABEL_ABV_equation)
        )


    }

}

@Preview
@Composable
fun CalculatorScreenPreview() {
    CalculatorScreen(
        abvUnit = AbvUnit.SIMPLE_GRAVITY,
        abvEquation = AbvEquation.SIMPLE
    )
}