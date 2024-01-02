package com.fair.tool_belt_abv.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.abv.shared.model.AbvUnit
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.ui.component.DefaultTextField

@Composable
fun ConverterScreen(
    gravityText: String,
    platoText: String,
    brixText: String,
    modifier: Modifier = Modifier,
    onValueChange: (AbvUnit, String) -> Unit = { _, _ -> }
) {
    Column(
        modifier = modifier.padding(sizes.medium),
        verticalArrangement = Arrangement.spacedBy(sizes.small)
    ) {
        DefaultTextField(
            value = gravityText,
            onValueChange = { change ->
                onValueChange(AbvUnit.SG, change)
            },
            imeAction = ImeAction.Done,
            label = stringResource(id = R.string.UNIT_TEXT_gravity)
        )

        DefaultTextField(
            value = platoText,
            onValueChange = { change ->
                onValueChange(AbvUnit.P, change)
            },
            imeAction = ImeAction.Done,
            label = stringResource(id = R.string.UNIT_TEXT_plato)
        )
        DefaultTextField(
            value = brixText,
            onValueChange = { change ->
                onValueChange(AbvUnit.B, change)
            },
            imeAction = ImeAction.Done,
            label = stringResource(id = R.string.UNIT_TEXT_brix)
        )
    }
}

@Preview
@Composable
fun ConverterScreenPreview() {
    ConverterScreen(
        "0",
        "0",
        "0"
    )
}
