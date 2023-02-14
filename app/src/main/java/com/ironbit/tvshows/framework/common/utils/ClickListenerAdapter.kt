package com.ironbit.tvshows.framework.common.utils

// Interface for propagate an Click event from Adapter to View
interface ClickListenerAdapter<T> {
    // Function trigger when a click is received
    fun onItemClicked(position: Int, item: T)
}
