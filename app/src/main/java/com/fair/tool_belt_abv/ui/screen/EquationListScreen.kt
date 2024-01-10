package com.fair.tool_belt_abv.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cerve.abv.shared.model.AbvEquation
import com.cerve.co.material3extension.designsystem.ExtendedTheme.colors
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EquationListScreen(
    selectedAbvEquation: AbvEquation,
    abvEquationList: List<AbvEquation>,
    modifier: Modifier = Modifier,
    onEquationUpdate: (name: String) -> Unit = { },
    onEquationSelect: (AbvEquation) -> Unit = { }
) {
    Column {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(sizes.small)
        ) {
            items(abvEquationList) { equation ->
                OutlinedCard(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = equation.type != AbvEquation.EquationType.Default,
                    onClick = { onEquationUpdate(equation.name) }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(sizes.small)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(sizes.small)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = equation.name.replaceFirstChar {
                                    if (it.isLowerCase()) {
                                        it.titlecase(Locale.ROOT)
                                    } else {
                                        it.toString()
                                    }
                                },
                                style = MaterialTheme.typography.labelLarge
                            )
                            RadioButton(
                                selected = selectedAbvEquation == equation,
                                onClick = { onEquationSelect(equation) }
                            )
                        }
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = CardDefaults.outlinedShape
                        ) {
                            Text(
                                modifier = Modifier.padding(sizes.small),
                                text = equation.equation,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        Row(
                            modifier = Modifier.align(Alignment.End),
                            horizontalArrangement = Arrangement.spacedBy(sizes.small),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            equation.timeStamp()?.let { stamp ->
                                Surface(
                                    modifier = Modifier,
                                    shape = CircleShape,
                                    color = colors.primaryContainer
                                ) {
                                    Text(
                                        modifier = Modifier.padding(sizes.small),
                                        text = stamp,
                                        style = MaterialTheme.typography.labelSmall
                                    )
                                }
                            }

                            Surface(
                                modifier = Modifier,
                                shape = CircleShape,
                                color = colors.primaryContainer
                            ) {
                                Text(
                                    modifier = Modifier.padding(sizes.small),
                                    text = equation.type.name,
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
