package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.co.material3extension.designsystem.ExtendedTheme
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.fair.tool_belt_abv.model.AbvEquation
import com.fair.tool_belt_abv.model.AbvUnit

@Composable
fun RadioGroupUnit(
    label: String,
    selected: AbvUnit,
    modifier: Modifier = Modifier,
    group: List<AbvUnit> = AbvUnit.values().asList(),
    onSelected: (AbvUnit) -> Unit = { }
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(sizes.small)
    ) {
        Text(
            text = label,
            style = ExtendedTheme.typography.bodySmall
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .selectableGroup(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(sizes.xSmall)
        ) {
            group.forEach { unit ->

                RadioCard(
                    text = stringResource(id = unit.textId),
                    selected = selected == unit,
                    modifier = Modifier.weight(1f)
                ) { onSelected(unit) }
            }
        }
    }
}

@Composable
fun RadioGroupEquation(
    label: String,
    selected: AbvEquation,
    modifier: Modifier = Modifier,
    group: List<AbvEquation> = AbvEquation.values().asList(),
    onSelected: (AbvEquation) -> Unit = { }
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(sizes.small)
    ) {
        Text(
            text = label,
            style = ExtendedTheme.typography.bodySmall
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .selectableGroup(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(sizes.xSmall)
        ) {
            group.forEach { unit ->

                RadioCard(
                    text = stringResource(id = unit.textId),
                    subText = unit.subtextId?.let { stringResource(id = it) },
                    selected = selected == unit,
                    modifier = Modifier.weight(1f)
                ) { onSelected(unit) }
            }
        }
    }
}

@Preview
@Composable
fun RadioGroupPreview() {
    RadioGroupUnit(
        modifier = Modifier.fillMaxWidth(),
        label = "Unit",
        selected = AbvUnit.B
    )
}
