package com.ironbit.tvshows.framework.common.utils

import java.text.SimpleDateFormat
import java.util.*

// Gets the device current date with a selected pattern
fun getCurrentDate(pattern: String): String {
    val time = Calendar.getInstance().time
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return formatter.format(time)
}
