package id.radhika.feature.schedule.data

import id.lesprivate.lib.data.model.SimpleResult
import id.radhika.feature.schedule.data.model.ScheduleBodyModel
import id.radhika.feature.schedule.screen.lesform.LesFormAdapter
import java.util.ArrayList

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
interface ScheduleRepository {

    suspend fun createSchedule(
        body: ScheduleBodyModel
    ): SimpleResult<Boolean>

}