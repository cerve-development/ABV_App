package com.fair.tool_belt_abv.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cerve.abv.shared.model.AbvTestEquation

@Composable
fun EquationListScreen(
    equations: List<AbvTestEquation.Entity>,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        items(equations) { equation ->
            Column {
                Text(text = equation.name)
                Text(text = equation.equation)
            }
        }

    }

}