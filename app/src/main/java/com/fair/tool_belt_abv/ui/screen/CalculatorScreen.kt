package com.fair.tool_belt_abv.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.fair.tool_belt_abv.model.AbvEquation
import com.fair.tool_belt_abv.model.AbvUnit
import com.fair.tool_belt_abv.ui.component.DashboardCard
import com.fair.tool_belt_abv.ui.component.DefaultTextField

@OptIn(ExperimentalMaterial3Api::class)
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
    onEquationSelect: (AbvEquation) -> Unit = { }
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

//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//
//            var expandedUnit by remember { mutableStateOf(false) }
//
//            ExposedDropdownMenuBox(
//                modifier = Modifier.weight(1f),
//                expanded = expandedUnit,
//                onExpandedChange = { expandedUnit = it },
//            ) {
//                TextField(
//                    // The `menuAnchor` modifier must be passed to the text field for correctness.
//                    modifier = Modifier.menuAnchor(),
//                    readOnly = true,
//                    value = stringResource(id = abvUnit.textId),
//                    onValueChange = {},
//                    shape = RoundedCornerShape(20),
//                    label = { Text(stringResource(id = R.string.LABEL_ABV_unit)) },
//                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedUnit) },
//                    colors = ExposedDropdownMenuDefaults.textFieldColors(
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent
//                    ),
//                )
//                ExposedDropdownMenu(
//                    expanded = expandedUnit,
//                    onDismissRequest = { expandedUnit = false },
//                ) {
//                    AbvUnit.entries.forEach { selectionOption ->
//                        DropdownMenuItem(
//                            text = { Text(stringResource(id = selectionOption.textId)) },
//                            onClick = {
//                                onUnitSelect(selectionOption)
//                                expandedUnit = false
//                            },
//                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
//                        )
//                    }
//                }
//            }
//
//            var expandedEquation by remember { mutableStateOf(false) }
//
//            ExposedDropdownMenuBox(
//                modifier = Modifier.weight(1f),
//                expanded = expandedEquation,
//                onExpandedChange = { expandedEquation = it },
//            ) {
//                TextField(
//                    // The `menuAnchor` modifier must be passed to the text field for correctness.
//                    modifier = Modifier.menuAnchor(),
//                    readOnly = true,
//                    value = stringResource(abvEquation.textId),
//                    onValueChange = {},
//                    shape = RoundedCornerShape(20),
//                    label = { Text(stringResource(id = R.string.LABEL_ABV_equation)) },
//                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedEquation) },
//                    colors = ExposedDropdownMenuDefaults.textFieldColors(
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent
//                    ),
//                )
//                ExposedDropdownMenu(
//                    expanded = expandedEquation,
//                    onDismissRequest = { expandedEquation = false },
//                ) {
//                    AbvEquation.entries.forEach { selectionOption ->
//                        DropdownMenuItem(
//                            text = { Text(stringResource(selectionOption.textId)) },
//                            onClick = {
//                                onEquationSelect(selectionOption)
//                                expandedEquation = false
//                            },
//                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
//                        )
//                    }
//                }
//            }
//
//        }
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
