package id.lesprivate.lib.ui.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


/**
 * Created by Radhika Yusuf Alifiansyah
 * on 15/Jun/2020
 **/

fun Calendar.toStringDate(): String {
    val sdf = SimpleDateFormat("EEEE, dd MMM, yy", Locale.ROOT)
    return sdf.format(this.time)
}

fun Calendar.toStringTime(): String {
    val sdf = SimpleDateFormat("HH:mm", Locale.ROOT)
    return sdf.format(this.time)
}

fun Calendar.toStringDateTime(): String {
    val sdf = SimpleDateFormat("EEEE, dd MMM, yy - HH:mm", Locale.ROOT)
    return sdf.format(this.time)
}

fun Long.toStringDateTime(): String {
    val sdf = SimpleDateFormat("EEEE, dd MMM, yy - HH:mm", Locale.ROOT)
    return sdf.format(this)
}