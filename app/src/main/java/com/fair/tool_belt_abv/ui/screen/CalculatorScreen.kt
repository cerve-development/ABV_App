package com.fair.tool_belt_abv.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.model.AbvEquation
import com.fair.tool_belt_abv.model.AbvUnit
import com.fair.tool_belt_abv.ui.component.DashboardCard
import com.fair.tool_belt_abv.ui.component.DefaultTextField
import com.fair.tool_belt_abv.ui.component.RadioGroupEquation
import com.fair.tool_belt_abv.ui.component.RadioGroupUnit

@Composable
fun CalculatorScreen(
    originalText: String,
    finalText: String,
    abvValue: String,
    attenuationValue: String,
    errorMessage: String?,
    abvUnit: AbvUnit,
    abvEquation: AbvEquation,
    modifier: Modifier = Modifier,
    onOriginalTextChange: (String) -> Unit = { },
    onFinalTextChange: (String) -> Unit = { },
    onUnitSelect: (AbvUnit) -> Unit = { },
    onEquationSelect: (AbvEquation) -> Unit = { }
) {
    Column(
        modifier = modifier.padding(sizes.medium),
        verticalArrangement = Arrangement.spacedBy(sizes.small),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DashboardCard(
            abvValue = abvValue,
            attenuationValue = attenuationValue,
            errorMessage = errorMessage
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(sizes.small),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DefaultTextField(
                value = originalText,
                onValueChange = { change ->
                    onOriginalTextChange(change)
                },
                label = stringResource(id = R.string.LABEL_ABV_original)
            )

            DefaultTextField(
                value = finalText,
                onValueChange = { change ->
                    onFinalTextChange(change)
                },
                imeAction = ImeAction.Done,
                label = stringResource(id = R.string.LABEL_ABV_final)
            )
        }

        RadioGroupUnit(
            selected = abvUnit,
            label = stringResource(id = R.string.LABEL_ABV_unit)
        ) { unit ->
            onUnitSelect(unit)
        }

        RadioGroupEquation(
            selected = abvEquation,
            label = stringResource(id = R.string.LABEL_ABV_equation)
        ) { equation ->
            onEquationSelect(equation)
        }
    }
}

@Preview
@Preview(device = Devices.PIXEL_XL)
@Preview(device = Devices.PIXEL_3A)
@Preview(heightDp = 1440, widthDp = 720)
@Preview(heightDp = 720, widthDp = 1440)
@Composable
fun CalculatorScreenPreview() {
    CalculatorScreen(
        originalText = "",
        finalText = "",
        abvValue = "0",
        attenuationValue = "0",
        errorMessage = null,
        abvUnit = AbvUnit.SG,
        abvEquation = AbvEquation.S
    )
}
