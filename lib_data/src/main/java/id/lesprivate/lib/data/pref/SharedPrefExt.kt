package id.lesprivate.lib.data.pref

import android.content.SharedPreferences
import id.lesprivate.lib.data.pref.delegates.*
import kotlin.properties.ReadWriteProperty

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 02/May/2020
 **/

fun SharedPreferences.string(
    key: String? = null,
    defaultValue: String = "",
    commit: Boolean = false
): ReadWriteProperty<Any, String> = StringPrefDelegates(this, key, defaultValue, commit)

fun SharedPreferences.long(
    key: String? = null,
    defaultValue: Long = 0L,
    commit: Boolean = false
): ReadWriteProperty<Any, Long> = LongPrefDelegates(this, key, defaultValue, commit)

fun SharedPreferences.int(
    key: String? = null,
    defaultValue: Int = 0,
    commit: Boolean = false
): ReadWriteProperty<Any, Int> = IntPrefDelegates(this, key, defaultValue, commit)

fun SharedPreferences.boolean(
    key: String? = null,
    defaultValue: Boolean = false,
    commit: Boolean = false
): ReadWriteProperty<Any, Boolean> = BooleanPrefDelegates(this, key, defaultValue, commit)

fun SharedPreferences.float(
    key: String? = null,
    defaultValue: Float = 0F,
    commit: Boolean = false
): ReadWriteProperty<Any, Float> = FloatPrefDelegates(this, key, defaultValue, commit)

fun SharedPreferences.stringSet(
    key: String? = null,
    defaultValue: MutableSet<String> = mutableSetOf(),
    commit: Boolean = false
): ReadWriteProperty<Any, MutableSet<String>> {
    return StringSetPrefDelegates(this, key, defaultValue, commit)
}