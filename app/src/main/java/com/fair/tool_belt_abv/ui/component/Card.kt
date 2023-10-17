package com.fair.tool_belt_abv.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.co.material3extension.designsystem.ExtendedTheme
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.fair.tool_belt_abv.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadioCard(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    subText: String? = null,
    onClick: () -> Unit = { }
) {

    Card(
        onClick = { onClick() },
        modifier = modifier,
        shape = ExtendedTheme.shapes.small,
        enabled = !selected,
        colors = CardDefaults.cardColors(
            containerColor = colors.tertiaryContainer
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(sizes.small)
                    .weight(1f),
                text = text,
                style = ExtendedTheme.typography.labelLarge,
                overflow = TextOverflow.Ellipsis
            )
            RadioButton(
                selected = selected,
                enabled = !selected,
                onClick = { onClick() }
            )
        }

        subText?.let { text ->
            Text(
                modifier = Modifier.padding(sizes.small),
                text = text,
                style = ExtendedTheme.typography.labelSmall,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun DashboardCard(
    attenuationValue: String,
    abvValue: String,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    isError: Boolean = !errorMessage.isNullOrEmpty()
) {
    val color = if (isError) {
        colors.errorContainer
    } else { colors.surfaceVariant }

    Card(
        modifier = modifier
            .aspectRatio(3f)
            .animateContentSize(),
        shape = ExtendedTheme.shapes.extraSmall,
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Text(
            modifier = Modifier.padding(sizes.small),
            text = stringResource(id = R.string.DEFAULT_RESULT_attenuation, attenuationValue),
            style = ExtendedTheme.typography.labelSmall,
            overflow = TextOverflow.Ellipsis
        )

//        ThemedDivider()

        Row(
            modifier = Modifier.padding(sizes.small),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(sizes.small)
        ) {
            TextAnimation(
                modifier = Modifier.weight(1f),
                value = abvValue
            ) { value ->
                Text(
                    text = stringResource(id = R.string.DEFAULT_RESULT_abv, value),
                    style = ExtendedTheme.typography.headlineLarge,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.End
                )
            }

            Text(
                modifier = Modifier,
                text = "Abv",
                style = ExtendedTheme.typography.labelSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        errorMessage?.let { text ->
            ThemedDivider()

            Text(
                modifier = Modifier.padding(sizes.small),
                text = text,
                color = color,
                style = ExtendedTheme.typography.labelSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun DashboardCardPreview() {
    DashboardCard(
        attenuationValue = "0",
        abvValue = "0",
        errorMessage = "Final Gravity can't be greater than original Gravity"
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
