package com.fair.tool_belt_abv.ui.screen

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.util.UUID.randomUUID
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

sealed class Screen<T>(open val value: T?) {
    data class Loading<T>(
        override val value: T? = null,
        val message: String? = null
    ) : Screen<T>(value)

    data class Error<T>(
        override val value: T,
        val message: String? = null
    ): Screen<T>(value)

    data class Loaded<T>(
        override val value: T
    ) : Screen<T>(value)

    data class Event<T>(
        override val value: T? = null,
        val type: EventType = EventType.None,
        val eventId: Int = (randomUUID()).hashCode()
    ) : Screen<T>(value)

    sealed interface EventType {
        data object None: EventType

        /**If route is null pop backstack*/
        data class Navigation(
            val route: String? = null,
            val args: String? = null
        ): EventType {
            fun withArgs(navArgs: String? = args) = navArgs?.let { "$route?$navArgs" } ?: route
        }

//        data class BottomSheet(
//            val title: StringResource,
//            val subtitle: StringResource? = null,
//            val header: StringResource? = null,
//            val footer: StringResource? = null,
//            val icon: ImageVector? = null
//        ) : EventType

    }

    @Composable
    fun StateWrapper(
        content: @Composable Screen<T>.() -> Unit
    ) {
        when(val state = this) {
            is Loading -> { }
            else -> { content(state) }
        }
    }

    @Composable
    fun ScreenWrapper(
        content: @Composable (T) -> Unit
    ) {
        when(val value = value) {
            null -> { }
            else -> { content(value) }
        }
    }
//    @OptIn(ExperimentalMaterial3Api::class)
//    @Composable
//    fun BottomSheetWrapper(
//        onDismiss: () -> Unit,
//        content: @Composable BoxScope.(EventType.BottomSheet) -> Unit
//    ) {
//        if (this@Screen is Event && this@Screen.type is EventType.BottomSheet) {
//            val sheet = this@Screen.type
//            val sheetState = remember {
//                SheetState(
//                    skipPartiallyExpanded = true,
//                    initialValue = SheetValue.Expanded
//                )
//            }
//
//            BottomSheet(
//                isVisible = true,
//                onDismissRequest = onDismiss,
//                state = sheetState,
////                icon = sheet.icon,
//                title = stringResource(sheet.title),
//                subtitle = sheet.subtitle?.let { stringResource(sheet.subtitle) }
//            ) { modifier ->
//                Box(modifier = modifier) { content(sheet) }
//            }
//        }
//    }
    @Composable
    fun LaunchedWrapper(
        content: suspend (EventType) -> Unit
    ) {
        if (this@Screen is Event) {
            LaunchedEffect(this) {
                content(this@Screen.type)
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ErrorWrapper(
        onDismiss: () -> Unit,
        content: @Composable ColumnScope.() -> Unit
    ) {
        if (this is Error) {
//            MaxContent(
//                topBar = {
//                    TopAppBar(
//                        title = { },
//                        actions = {
//                            DefaultIconButton(
//                                imageVector = Icons.Default.Close
//                            ) { onDismiss() }
//                        }
//                    )
//                },
//                title = stringResource(kmmres.strings.error_label),
//                subtitle = message,
//                body = content
//            )
        }
    }

    companion object {

        fun <T> screenMutableState(
            initial: Screen<T>
        ) = MutableStateFlow(initial)
        fun <T> Flow<Screen<T>>.asScreenStateIn(
            scope: CoroutineScope,
            started: SharingStarted = SharingStarted
                .WhileSubscribed(replayExpiration = Duration.ZERO),
            initialValue: Screen<T> = Loading(),
        ) : StateFlow<Screen<T>> = stateIn(
            scope = scope,
            started = started,
            initialValue = initialValue
        )

        suspend fun <T> MutableStateFlow<Screen<T>>.load(
            result: suspend (T?) -> Screen<T>
        ) {
            update { Loading(it.value) }
            update { result(it.value) }
        }

        @Deprecated("")
        fun <R, T: Screen<R>> Flow<T>.asShareIn(
            scope: CoroutineScope,
            started: SharingStarted = SharingStarted.WhileSubscribed(
                stopTimeout = 0.seconds,
                replayExpiration = 0.seconds
            ),
        ): SharedFlow<Screen<R>> {
            return shareIn(
                scope = scope,
                started = started,
                replay = 0
            )
        }
        @Deprecated("")
        fun <T> Flow<T>.screenStateIn(
            scope: CoroutineScope,
            started: SharingStarted = SharingStarted.WhileSubscribed(
                stopTimeout = 5.seconds,
                replayExpiration = 0.seconds
            ),
            initialValue: Screen<T> = Loading()
        ): StateFlow<Screen<T>> {
            return map { value ->
                Loaded(value)
            }.onStart {
                Loading<T>()
            }.stateIn(
                scope = scope,
                started = started,
                initialValue = initialValue
            )
        }
    }
}
