package id.radhika.feature.schedule.data.impl

import id.lesprivate.lib.data.api.ApiService
import id.lesprivate.lib.data.model.SimpleResult
import id.lesprivate.lib.data.model.getExceptionResponse
import id.radhika.feature.schedule.FeatureScheduleModule
import id.radhika.feature.schedule.data.ScheduleRepository
import id.radhika.feature.schedule.data.api.ScheduleService
import id.radhika.feature.schedule.data.model.ScheduleBodyModel

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
class ScheduleRepositoryImpl(
    private val scheduleService: ScheduleService = ApiService.createService(
        ScheduleService::class.java,
        FeatureScheduleModule.SCHEDULE_MODULE_BASE_URL
    )
) : ScheduleRepository {

    override suspend fun createSchedule(body: ScheduleBodyModel): SimpleResult<Boolean> {
        return try {
            val result = scheduleService.createSchedule(body)
            if (result.code in 200..205) {
                SimpleResult(true, result.data, result.message)
            } else {
                SimpleResult(false, result.data, result.message)
            }
        } catch (e: Exception) {
            getExceptionResponse<Boolean>(e).let { SimpleResult(it.isSuccess, false, it.message) }
        }
    }
}