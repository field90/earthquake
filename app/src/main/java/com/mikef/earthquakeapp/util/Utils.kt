package com.mikef.earthquakeapp.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(epochMillis: Long?): String {
    if (epochMillis == null) return "Unknown"
    val date = Date(epochMillis)
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    return formatter.format(date)
}