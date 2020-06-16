package id.radhika.feature.schedule.screen

import android.os.Bundle
import id.lesprivate.lib.mvvm.BaseActivity
import id.radhika.feature.schedule.R
import id.radhika.feature.schedule.screen.lesform.LesFormScreen

class ScheduleActivity : BaseActivity() {

    private val firstScreen by lazy { LesFormScreen() }

    override fun frameLayoutId() = R.id.frameSchedule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        replaceScreen(firstScreen)
    }
}
