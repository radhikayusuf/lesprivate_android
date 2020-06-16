package id.lesprivate.lib.data.pref.delegates

import android.annotation.SuppressLint
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class StringSetPrefDelegates(
    private val pref: SharedPreferences,
    private val key: String?,
    private val defaultValue: MutableSet<String>,
    private val commit: Boolean
) : ReadWriteProperty<Any, MutableSet<String>> {

    override fun getValue(thisRef: Any, property: KProperty<*>): MutableSet<String> =
        pref.getStringSet(key ?: property.name, defaultValue) ?: defaultValue

    @SuppressLint("ApplySharedPref")
    override fun setValue(thisRef: Any, property: KProperty<*>, value: MutableSet<String>) {
        val editor = pref.edit().putStringSet(key ?: property.name, value)
        if (commit) editor.commit()
        else editor.apply()
    }
}