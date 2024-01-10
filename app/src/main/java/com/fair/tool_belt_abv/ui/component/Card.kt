package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.rounded.Percent
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.cerve.co.material3extension.designsystem.ExtendedTheme.typography
import com.cerve.co.material3extension.designsystem.rounded
import com.fair.tool_belt_abv.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadioCard(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    OutlinedCard(
        onClick = { onClick() },
        modifier = modifier,
        enabled = !selected,
        colors = CardDefaults.cardColors(
            containerColor = colors.tertiaryContainer
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier
                    .padding(sizes.small)
                    .weight(1f),
                text = text,
                style = typography.labelSmall,
                overflow = TextOverflow.Ellipsis
            )
            RadioButton(
                selected = selected,
                enabled = !selected,
                onClick = { onClick() }
            )
        }
    }
}

@Composable
fun DashboardCard(
    attenuationValue: String,
    abvValue: String,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        Row {
            ResultContent(
                modifier = Modifier.weight(1f),
                label = stringResource(id = R.string.DEFAULT_RESULT_Attenuation_Abbreviation),
                value = attenuationValue
            )
            Divider(
                modifier = Modifier
                    .fillMaxHeight() // fill the max height
                    .width(DividerDefaults.Thickness)
            )
            ResultContent(
                modifier = Modifier.weight(1.5f),
                label = stringResource(id = R.string.DEFAULT_ABV_LABEL),
                value = abvValue
            )
        }
    }
}

@Composable
fun ResultContent(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    valueStyle: TextStyle = MaterialTheme.typography.headlineLarge
) {
    var multiplier by remember(value.length) { mutableFloatStateOf(1f) }

    Column(
        modifier = modifier.padding(sizes.small),
        verticalArrangement = Arrangement.spacedBy(sizes.small)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterHorizontally)
                    .wrapContentHeight(),
                text = value,
                textAlign = TextAlign.Center,
                style = valueStyle.copy(
                    fontWeight = FontWeight.Black,
                    fontSize = valueStyle.fontSize * multiplier
                ),
                softWrap = true,
                maxLines = 1,
                onTextLayout = {
                    if (it.hasVisualOverflow) {
                        multiplier *= 0.99f
                    }
                }
            )

            ThemedChip(
                modifier = Modifier.align(Alignment.End)
            ) { chipModifier ->
                Icon(
                    modifier = chipModifier.size(sizes.medium),
                    imageVector = rounded.Percent,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun DashboardCardPreview() {
    DashboardCard(
        attenuationValue = "0.0",
        abvValue = "0.0"
    )
}

@Preview
@Composable
fun RadioCardPreview() {
    Row {
        RadioCard(
            modifier = Modifier.weight(1f),
            text = "Plato",
            selected = true
        )
        RadioCard(
            modifier = Modifier.weight(1f),
            text = "Brix",
            selected = false
        )
    }
}
