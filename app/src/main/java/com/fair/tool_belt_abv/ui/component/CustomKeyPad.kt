package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Backspace
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.cerve.abv.shared.model.AbvEquation
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.ui.model.KeyPadValue

@Composable
fun CustomKeyboard(
    modifier: Modifier = Modifier,
    onClear: () -> Unit = { },
    onRemoveLast: () -> Unit = { },
    onEqualKeyClick: (() -> Unit)? = null,
    onKeyClick: (String) -> Unit = { }
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.End)
                .padding(sizes.medium),
            horizontalArrangement = Arrangement.spacedBy(sizes.medium)
        ) {
            Button(onClick = { onKeyClick(AbvEquation.StaticValues.OG.name) }) {
                Text(text = AbvEquation.StaticValues.OG.name)
            }

            Button(onClick = { onKeyClick(AbvEquation.StaticValues.FG.name) }) {
                Text(text = AbvEquation.StaticValues.FG.name)
            }

            TextButton(onClick = { onClear() }) {
                Text(text = stringResource(id = R.string.LABEL_KEY_PAD_clear))
            }

            IconButton(onClick = { onRemoveLast() }) {
                Icon(imageVector = Icons.Outlined.Backspace, null)
            }
        }
        Divider()
        CustomKeyPad(
            onEqualKeyClick = onEqualKeyClick,
            onKeyClick = onKeyClick
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomKeyPad(
    modifier: Modifier = Modifier,
    maxItemsInEachRow: Int = 5,
    keyBoardPadding: Dp = sizes.medium,
    onEqualKeyClick: (() -> Unit)? = null,
    onKeyClick: (String) -> Unit = { }
) {
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val size by remember {
            derivedStateOf {
                (minOf(maxHeight, maxWidth) / (maxItemsInEachRow) - keyBoardPadding)
            }
        }

        FlowRow(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(keyBoardPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalArrangement = Arrangement.SpaceEvenly,
            maxItemsInEachRow = maxItemsInEachRow
        ) {
            KeyPadValue.entries.forEach { key ->
                SurfaceButton(
                    modifier = Modifier.size(size),
                    text = key.label
                ) {
                    when (key) {
                        KeyPadValue.Equal -> onEqualKeyClick?.invoke() ?: onKeyClick(key.text)
                        else -> onKeyClick(key.text)
                    }
                }
            }
        }
    }
}

@Preview
@Preview(device = Devices.PIXEL_XL)
@Preview(device = Devices.PIXEL_3A)
@Composable
fun CustomKeyPadPreview() {
    CustomKeyboard()
}
