package com.fair.tool_belt_abv.data

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LifecycleSubscriptionsContainer (context: Context, lifecycle: Lifecycle) :
    SubscriptionsContainer(context), LifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun clearSubscriptions() {
        super.clearSubscriptions()
    }
}