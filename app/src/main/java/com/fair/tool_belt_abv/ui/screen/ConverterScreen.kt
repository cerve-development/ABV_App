package com.fair.tool_belt_abv.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.abv.shared.model.AbvUnit
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.ui.component.DefaultTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConverterScreen(
    gravityText: String,
    platoText: String,
    brixText: String,
    modifier: Modifier = Modifier,
    onValueChange: (AbvUnit, String) -> Unit = { _, _ -> }
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.NAV_DESTINATION_converter)) })
        },
        contentWindowInsets = WindowInsets(0)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(sizes.medium),
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
                    onValueChange(AbvUnit.Plato, change)
                },
                imeAction = ImeAction.Done,
                label = stringResource(id = R.string.UNIT_TEXT_plato)
            )
            DefaultTextField(
                value = brixText,
                onValueChange = { change ->
                    onValueChange(AbvUnit.Brix, change)
                },
                imeAction = ImeAction.Done,
                label = stringResource(id = R.string.UNIT_TEXT_brix)
            )
        }
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
