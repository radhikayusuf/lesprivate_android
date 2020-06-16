package id.radhika.feature.schedule

import id.lesprivate.lib.mvvm.BaseModule

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
class FeatureScheduleModule : BaseModule() {

    companion object {
        const val SCHEDULE_MODULE_BASE_URL = "http://bimbel.zaelani.me/"

        private val INSTANCE by lazy { FeatureScheduleModule() }
        fun get() = INSTANCE
    }
}