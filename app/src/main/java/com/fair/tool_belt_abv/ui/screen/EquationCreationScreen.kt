package com.fair.tool_belt_abv.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.fair.tool_belt_abv.ui.component.SurfaceButton

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EquationCreationScreen(
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {

        val size by remember {
            derivedStateOf {
                (minOf(maxHeight, maxWidth) / 6 )
            }
        }
        Divider()
        FlowColumn(
            modifier = Modifier
                .fillMaxWidth()
                .matchParentSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalArrangement = Arrangement.SpaceEvenly,
            maxItemsInEachColumn = 5
        ) {
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalArrangement = Arrangement.SpaceEvenly,
                maxItemsInEachRow = 4
            ) {
                SurfaceButton(
                    modifier = Modifier.size(size),
                    text = "C",
                ) { }

                SurfaceButton(
                    modifier = Modifier.size(size),
                    text = "()",
                ) { }

                SurfaceButton(
                    modifier = Modifier.size(size),
                    text = "%"
                ) { }

                SurfaceButton(
                    modifier = Modifier.size(size),
                    text = "÷"
                ) { }
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalArrangement = Arrangement.SpaceEvenly,
                maxItemsInEachRow = 4
            ) {

                (7..9).forEach { key ->
                    SurfaceButton(
                        modifier = Modifier.size(size),
                        text = "$key",
                    ) { }
                }

                SurfaceButton(
                    modifier = Modifier.size(size),
                    text = "×"
                ) { }

            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalArrangement = Arrangement.SpaceEvenly,
                maxItemsInEachRow = 4
            ) {
                (4..6).forEach { key ->
                    SurfaceButton(
                        modifier = Modifier.size(size),
                        text = "$key",
                    ) { }
                }
                SurfaceButton(
                    modifier = Modifier.size(size),
                    text = "-",
                ) { }
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalArrangement = Arrangement.SpaceEvenly,
                maxItemsInEachRow = 4
            ) {
                (1..3).forEach { key ->
                    SurfaceButton(
                        modifier = Modifier.size(size),
                        text = "$key",
                    ) { }
                }

                SurfaceButton(
                    modifier = Modifier.size(size),
                    text = "+",
                ) { }
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalArrangement = Arrangement.SpaceEvenly,
                maxItemsInEachRow = 4
            ) {
                SurfaceButton(
                    modifier = Modifier.size(size),
                    text = "+/-",
                ) { }

                SurfaceButton(
                    modifier = Modifier.size(size),
                    text = "0",
                ) { }
                SurfaceButton(
                    modifier = Modifier.size(size),
                    text = ".",
                ) { }
                SurfaceButton(
                    modifier = Modifier.size(size),
                    text = "=",
                ) { }
            }
        }
    }
}

@Preview
@Preview(device = Devices.PIXEL_XL)
@Preview(device = Devices.PIXEL_3A)
@Composable
fun EquationCreationScreenPreview() {
    Column(

    ) {
        Spacer(modifier = Modifier.weight(1f))
        EquationCreationScreen(
            modifier = Modifier.weight(1f)
        )
    }
}