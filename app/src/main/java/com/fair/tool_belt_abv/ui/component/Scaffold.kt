package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CerveScaffold(
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = WindowInsets(0),
    topBar: @Composable () -> Unit = { },
    content: @Composable () -> Unit = { },
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = topBar,
        contentWindowInsets = windowInsets
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            content()
        }
    }

}