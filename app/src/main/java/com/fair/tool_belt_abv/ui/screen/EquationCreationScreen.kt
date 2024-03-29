package com.fair.tool_belt_abv.ui.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.abv.shared.model.AbvEquation.StaticValues
import com.cerve.co.material3extension.designsystem.ExtendedTheme
import com.cerve.co.material3extension.designsystem.rounded
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.ui.component.CerveScaffold
import com.fair.tool_belt_abv.ui.component.CustomKeyboard
import com.fair.tool_belt_abv.ui.viewmodel.EquationCreationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EquationCreationScreen(
    state: EquationCreationViewModel.UiState,
    modifier: Modifier = Modifier,
    errorMessage: String = stringResource(id = R.string.LABEL_ABV_RESULT_ERROR),
    onNameUpdate: (String) -> Unit = { },
    onEquationUpdate: (TextFieldValue) -> Unit = { },
    onEquationDelete: (String) -> Unit = { },
    onEquationSave: (EquationCreationViewModel.UiState) -> Unit = { },
    onBackClick: () -> Unit = { }
) {
    var editable by remember { mutableStateOf(false) }
    val angle: Float by animateFloatAsState(
        targetValue = if (editable) 90f else 0f,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioMediumBouncy
        ),
        label = ""
    )

    val nameFocusRequester = remember { FocusRequester() }
    val equationFocusRequester = remember { FocusRequester() }

    LaunchedEffect(editable) {
        if (editable) {
            nameFocusRequester.requestFocus()
        } else {
            equationFocusRequester.requestFocus()
        }
    }

    CerveScaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        if (editable) {
                            editable = false
                        } else { onBackClick() }
                    }) {
                        Icon(
                            modifier = Modifier.rotate(angle),
                            imageVector = rounded.ChevronLeft,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    TextField(
                        modifier = Modifier
                            .focusRequester(nameFocusRequester)
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null
                            ) { editable = true },
                        enabled = editable,
                        value = state.name,
                        onValueChange = { value -> onNameUpdate(value) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        placeholder = { Text(stringResource(id = R.string.LABEL_ABV_EQUATION_NAME)) },
                        singleLine = true
                    )
                },
                actions = {
                    if (state.isDeletable) {
                        IconButton(onClick = { onEquationDelete(state.name) }) {
                            Icon(
                                imageVector = Icons.TwoTone.Delete,
                                contentDescription = null
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            CustomKeyboard(
                modifier = Modifier
                    .fillMaxHeight(0.65f)
                    .windowInsetsPadding(BottomAppBarDefaults.windowInsets),
                onClear = { onEquationUpdate(TextFieldValue()) },
                onRemoveLast = {
                    try {
                        val cursor = state.equation.selection.start - 1
                        val equation = StringBuilder(state.equation.text)
                            .deleteAt(cursor).toString()

                        val stateCopy = state.equation
                            .copy(text = equation, selection = TextRange(cursor))

                        onEquationUpdate(stateCopy)
                    } catch (_: Exception) { }
                },
                onEqualKeyClick = { onEquationSave(state) }
            ) { keyValue ->
                var cursor = state.equation.selection.start
                val equation = StringBuilder(state.equation.text)
                    .insert(cursor, keyValue).toString()

                when (keyValue) {
                    StaticValues.OG.name, StaticValues.FG.name -> cursor += 2
                    else -> cursor++
                }
                val stateCopy = state.equation
                    .copy(text = equation, selection = TextRange(cursor + 1))

                onEquationUpdate(stateCopy)
            }
        },
        windowInsets = ScaffoldDefaults.contentWindowInsets
    ) {
        Column(modifier = Modifier.padding(ExtendedTheme.sizes.medium)) {
            val result = remember(state.solution) { state.solution ?: errorMessage }

            Text(
                text = stringResource(id = R.string.LABEL_ABV_RESULT, result),
                style = typography.labelSmall
            )

            CompositionLocalProvider(LocalTextInputService provides null) {
                TextField(
                    modifier = Modifier
                        .focusRequester(equationFocusRequester)
                        .fillMaxSize()
                        .wrapContentHeight(),
                    value = state.equation,
                    onValueChange = { value -> onEquationUpdate(value) },
                    textStyle = typography.headlineLarge.copy(textAlign = TextAlign.Right),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}

@Preview
@Preview(device = Devices.PIXEL_XL)
@Preview(device = Devices.PIXEL_3A)
@Composable
fun EquationCreationScreenPreview() {
    EquationCreationScreen(state = EquationCreationViewModel.UiState())
}
