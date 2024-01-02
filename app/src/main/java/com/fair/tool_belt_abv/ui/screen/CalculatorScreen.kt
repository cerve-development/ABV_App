package com.fair.tool_belt_abv.ui.screen

import androidx.compose.foundation.background
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
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.model.AbvUnit
import com.fair.tool_belt_abv.ui.component.DashboardCard
import com.fair.tool_belt_abv.ui.component.DefaultTextField

@Composable
fun CalculatorScreen(
    originalText: String,
    finalText: String,
    abvValue: String,
    attenuationValue: String,
    errorMessage: String?,
    abvUnit: AbvUnit,
//    abvEquation: AbvEquation,
    modifier: Modifier = Modifier,
    onOriginalTextChange: (String) -> Unit = { },
    onFinalTextChange: (String) -> Unit = { },
    onUnitSelect: (AbvUnit) -> Unit = { },
    onEquationSelect: (String) -> Unit = { }
) {
    Column(
        modifier = modifier
            .background(color = colors.surface)
            .padding(sizes.medium),
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

    }
}

@Preview
@Preview(device = Devices.PIXEL_XL)
@Preview(device = Devices.PIXEL_3A)
@Composable
fun CalculatorScreenPreview() {
    CalculatorScreen(
        originalText = "",
        finalText = "",
        abvValue = "0",
        attenuationValue = "0",
        errorMessage = null,
        abvUnit = AbvUnit.SG,
//        abvEquation = AbvEquation.S
    )
}
