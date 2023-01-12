package com.fair.tool_belt_abv.ui.component

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

    val (contentColor, color) = if (selected) {
        Pair(colors.primary.copy(alpha = alphas.small_10), colors.primary)
    } else {
        Pair(colors.surface, colors.onSurface)
    }

    OutlinedCard(
        onClick = { onClick() },
        modifier = modifier,
        shape = ExtendedTheme.shapes.extraSmall,
        border = themedBorder(color = color),
        colors = CardDefaults.outlinedCardColors(containerColor = contentColor)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
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
        modifier = modifier.fillMaxWidth(),
        shape = ExtendedTheme.shapes.extraSmall,
        border = themedBorder(color = color),
        colors = CardDefaults.outlinedCardColors(containerColor = contentColor)
    ) {
        Text(
            modifier = Modifier.padding(sizes.small),
            text = stringResource(id = R.string.default_Attenuation, attenuationValue),
            color = color,
            style = ExtendedTheme.typography.labelSmall,
            overflow = TextOverflow.Ellipsis
        )

        ThemedDivider()

        Row(
            modifier = Modifier.padding(sizes.small),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(sizes.small)
        ){
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.default_Abv, abvValue),
                color = color,
                style = ExtendedTheme.typography.headlineLarge,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.End
            )

            Text(
                modifier = Modifier,
                text = "Abv",
                color = color,
                style = ExtendedTheme.typography.labelSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        ThemedDivider()

        errorMessage?.let { text ->
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