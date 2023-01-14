package com.fair.tool_belt_abv.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.co.material3extension.designsystem.ExtendedTheme
import com.cerve.co.material3extension.designsystem.ExtendedTheme.alphas
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
    val (contentColor, color, stroke) = if (selected) {
        Triple(colors.primary.copy(alpha = alphas.large_60), Color.Transparent, sizes.default)
    } else {
        Triple(colors.surface, colors.onSurface, sizes.xSmall / 2)
    }

    OutlinedCard(
        onClick = { onClick() },
        modifier = modifier,
        shape = ExtendedTheme.shapes.extraSmall,
        enabled = !selected,
        border = themedBorder(
            width = stroke,
            color = color
        ),
        colors = CardDefaults.outlinedCardColors(containerColor = contentColor)
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
                style = ExtendedTheme.typography.bodySmall,
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
    val (contentColor, color) = if (isError) {
        Pair(colors.errorContainer, colors.error)
    } else {
        Pair(colors.surface, colors.onSurface)
    }

    OutlinedCard(
        modifier = modifier.fillMaxWidth()
            .animateContentSize(),
        shape = ExtendedTheme.shapes.extraSmall,
        border = themedBorder(color = color),
        colors = CardDefaults.outlinedCardColors(containerColor = contentColor)
    ) {
        Text(
            modifier = Modifier.padding(sizes.small),
            text = stringResource(id = R.string.DEFAULT_RESULT_attenuation, attenuationValue),
            color = color,
            style = ExtendedTheme.typography.labelSmall,
            overflow = TextOverflow.Ellipsis
        )

        ThemedDivider()

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
                    color = color,
                    style = ExtendedTheme.typography.headlineLarge,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.End
                )
            }

            Text(
                modifier = Modifier,
                text = "Abv",
                color = color,
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
