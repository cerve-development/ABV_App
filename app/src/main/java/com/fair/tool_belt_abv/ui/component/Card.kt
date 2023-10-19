package com.fair.tool_belt_abv.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.co.material3extension.designsystem.ExtendedTheme
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.cerve.co.material3extension.designsystem.ExtendedTheme.typography
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
                style = typography.labelLarge,
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
                style = typography.labelSmall,
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
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(),
        border = themedBorder(color = color),
        colors = CardDefaults.outlinedCardColors(containerColor = contentColor)
    ) {
        Text(
            modifier = Modifier.padding(sizes.small),
            text = stringResource(
                id = R.string.DEFAULT_RESULT_attenuation,
                attenuationValue
            ),
            style = typography.labelMedium
        )

        ThemedDivider(color = color)

        AbvContent(
            modifier = Modifier.padding(sizes.small),
            abv = abvValue
        )

        errorMessage?.let { text ->
            ThemedDivider(color = color)

            Text(
                modifier = Modifier.padding(sizes.small),
                text = text,
                style = typography.labelMedium
            )
        }
    }
}

@Composable
fun AbvContent(
    abv: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(sizes.small)
    ) {
        TextAnimation(
            modifier = Modifier.weight(1f),
            value = abv
        ) { value ->
            Text(
                text = stringResource(id = R.string.DEFAULT_RESULT_abv, value),
                textAlign = TextAlign.End,
                style = typography.displayMedium.copy(fontWeight = FontWeight.Black)
            )
        }

        ThemedChip { chipModifier ->
            Text(
                modifier = chipModifier,
                text = stringResource(id = R.string.DEFAULT_ABV_LABEL),
                style = typography.labelMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AbvContentPreview() {
    AbvContent(abv = "1.54")
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
