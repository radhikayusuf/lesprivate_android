package id.lesprivate.app

import android.app.Application
import id.radhika.feature.schedule.FeatureScheduleModule
import id.radhika.feature.schedule.screen.ScheduleActivity
import id.radhika.feature.user.FeatureUserModule

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 05/Apr/2020
 **/
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FeatureUserModule.get().apply {
            application = { this@MainApplication }
            screenAfterLogin = { ScheduleActivity::class.java }
        }

        FeatureScheduleModule.get().apply { application = { this@MainApplication } }
    }
}