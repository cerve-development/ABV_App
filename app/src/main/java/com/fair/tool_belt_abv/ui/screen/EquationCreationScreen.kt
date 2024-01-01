package com.fair.tool_belt_abv.ui.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.preferences.PreferencesProto.Value
import com.cerve.abv.shared.domain.NewEquationUseCase
import com.cerve.abv.shared.model.AbvTestEquation
import com.cerve.co.material3extension.designsystem.ExtendedTheme
import com.cerve.co.material3extension.designsystem.rounded
import com.fair.tool_belt_abv.ui.component.CustomKeyboard
import com.fair.tool_belt_abv.ui.viewmodel.EquationCreationViewModel.Companion.EMPTY_STRING
import com.fair.tool_belt_abv.R
import com.fair.tool_belt_abv.ui.viewmodel.EquationCreationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EquationCreationScreen(
    state: EquationCreationViewModel.UiState,
    modifier: Modifier = Modifier,
    errorMessage: String = stringResource(id = R.string.LABEL_ABV_RESULT_ERROR),
    onNameUpdate: (String) -> Unit = { },
    onEquationUpdate: (String) -> Unit = { },
    onEquationSave: (EquationCreationViewModel.UiState) -> Unit = { },
    onBackClick: () -> Unit = { }
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            var editable by remember { mutableStateOf(false) }
            val angle: Float by animateFloatAsState(
                targetValue = if(editable) 360f else 0f,
                animationSpec = spring(
                    stiffness = Spring.StiffnessLow,
                    dampingRatio = Spring.DampingRatioMediumBouncy
                ), label = ""
            )

            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        if(editable) {
                            editable = false
                        } else { onBackClick() }
                    }) {
                        Icon(
                            modifier = Modifier.rotate(angle),
                            imageVector = if (editable) {
                                rounded.ArrowUpward
                            } else { rounded.ArrowBack },
                            contentDescription = null
                        )
                    }
                },
                title = {
                    val focusRequester = FocusRequester()

                    LaunchedEffect(editable) {
                        if(editable) {
                            focusRequester.requestFocus()
                        }
                    }

                    TextField(
                        modifier = Modifier
                            .focusRequester(focusRequester)
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null
                            ) { editable = true },
                        enabled = editable,
                        value = state.name,
                        onValueChange = { value -> onNameUpdate(value) },
                        colors = TextFieldDefaults.colors(
                            disabledTextColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                        ),
                        placeholder = { Text(stringResource(id = R.string.LABEL_ABV_EQUATION_NAME)) },
                        singleLine = true
                    )
                }
            )
        },
        bottomBar = {
            CustomKeyboard(
                modifier = Modifier
                    .fillMaxHeight(0.65f)
                    .windowInsetsPadding(BottomAppBarDefaults.windowInsets),
                onClear = { onEquationUpdate(EMPTY_STRING) },
                onRemoveLast = {
                    val equation = when(state.equation.takeLast(2)) {
                        AbvTestEquation.StaticValues.OG.name -> { state.equation.dropLast(2) }
                        AbvTestEquation.StaticValues.FG.name -> { state.equation.dropLast(2) }
                        else -> state.equation.dropLast(1)
                    }
                    onEquationUpdate(equation)
                },
                onEqualKeyClick = { onEquationSave(state) }
            ) { keyValue -> onEquationUpdate(state.equation + keyValue) }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(ExtendedTheme.sizes.medium)
        ) {
            val result = remember(state.solution) { state.solution ?: errorMessage }

            Text(
                text = stringResource(id = R.string.LABEL_ABV_RESULT, result),
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
                    .align(Alignment.CenterHorizontally),
                text = state.equation,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }

}

@OptIn(ExperimentalLayoutApi::class)
fun Modifier.clearFocusOnKeyboardDismiss(): Modifier = composed {
    var isFocused by remember { mutableStateOf(false) }
    var keyboardAppearedSinceLastFocused by remember { mutableStateOf(false) }
    if (isFocused) {
        val imeIsVisible = WindowInsets.isImeVisible
        val focusManager = LocalFocusManager.current
        LaunchedEffect(imeIsVisible) {
            if (imeIsVisible) {
                keyboardAppearedSinceLastFocused = true
            } else if (keyboardAppearedSinceLastFocused) {
                focusManager.clearFocus()
            }
        }
    }
    onFocusEvent {
        if (isFocused != it.isFocused) {
            isFocused = it.isFocused
            if (isFocused) {
                keyboardAppearedSinceLastFocused = false
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