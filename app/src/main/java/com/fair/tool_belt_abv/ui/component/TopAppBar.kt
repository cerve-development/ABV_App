package com.fair.tool_belt_abv.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.cerve.co.material3extension.designsystem.ExtendedTheme.sizes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CerveTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    showDivider: Boolean = false,
    navigationIcon: @Composable () -> Unit = { },
    actions: @Composable RowScope.() -> Unit = { }
) {
    Box(modifier = modifier, contentAlignment = Alignment.BottomCenter) {
        TopAppBar(
            colors = topAppBarColors(containerColor = Color.Transparent),
            navigationIcon = navigationIcon,
            actions = actions,
            title = { Text(text = title) }
        )
        if (showDivider) { ThemedDivider() }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> CerveTabTopAppBar(
    items: List<T>,
    initial: Int = 0,
    state: PagerState = rememberPagerState(initial) { items.size },
    content: @Composable (T) -> Unit = { }
) {
    val scope = rememberCoroutineScope()

    ScrollableTabRow(
        modifier = Modifier.fillMaxWidth(),
        selectedTabIndex = state.currentPage,
        indicator = { tabPositions ->
            TabIndicator(tabPositions[state.currentPage])
        },
        divider = { },
        edgePadding = sizes.medium
    ) {
        items.forEachIndexed { index, item ->
            Tab(
                modifier = Modifier,
                selected = state.currentPage == index,
                onClick = {
                    scope.launch {
                        state.animateScrollToPage(index)
                    }
                }
            ) {
                Box(modifier = Modifier.padding(sizes.medium)) {
                    content(item)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CerveTopAppBarPreview() {
    CerveTopAppBar("Hello world")
}
