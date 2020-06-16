package id.radhika.feature.user

import android.app.Activity
import id.lesprivate.lib.mvvm.BaseModule

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 22/May/2020
 **/
class FeatureUserModule : BaseModule() {

    lateinit var screenAfterLogin: (() -> Class<*>)

    companion object {
        private val INSTANCE by lazy { FeatureUserModule() }
        fun get() = INSTANCE
    }
}