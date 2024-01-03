package com.fair.tool_belt_abv.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.fair.tool_belt_abv.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.util.UUID.randomUUID
import kotlin.time.Duration

sealed class Screen<T>(val value: T) {
    data class Loading<T>(
        private val state: T
    ) : Screen<T>(state)

    data class Error<T>(
        private val state: T,
        val message: String? = null
    ): Screen<T>(state)

    data class Loaded<T>(
        private val state: T
    ) : Screen<T>(state)

    data class Event<T>(
        private val state: T,
        val type: EventType = EventType.None,
        val eventId: Int = (randomUUID()).hashCode()
    ) : Screen<T>(state)

    fun <R> mapValue(data: R) : Screen<R> {
        return when(this) {
            is Loading -> this.copy(state = data as T) as Screen.Loading<R>
            is Loaded -> this.copy(state = data as T) as Screen.Loaded<R>
            is Event -> this.copy(state = data as T) as Screen.Event<R>
            is Error -> this.copy(state = data as T) as Screen.Error<R>
        }
    }

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
        if (value == null) {
            //TODO investigate why non null still showing
        } else { content(value as T) }
    }

    @Composable
    fun LaunchedWrapper(
        content: suspend (EventType) -> Unit
    ) {
        LaunchedEffect(this) {
            if (this@Screen is Event) {
                content(this@Screen.type)
            }
        }
    }

    companion object {


        fun <T> screenMutableState(
            initial: (Screen<T>)
        ) = MutableStateFlow(initial)

        fun <T> Flow<Screen<T>>.asScreenStateIn(
            initialValue: T,
            scope: CoroutineScope,
            started: SharingStarted = SharingStarted
                .WhileSubscribed(replayExpiration = Duration.ZERO),
        ) : StateFlow<Screen<T>> = stateIn(
            scope = scope,
            started = started,
            initialValue = Loading(initialValue)
        )


        suspend fun <T> MutableStateFlow<Screen<T>>.loading(
            result: suspend (T) -> Screen<T>
        ) {
            update { Loading(it.value) }
            update { result(it.value) }
        }

        inline fun <T> MutableStateFlow<Screen<T>>.loaded(
            function: (Screen<T>) -> T
        ) {
            update { Loaded(function(it)) }
        }

    }
}
