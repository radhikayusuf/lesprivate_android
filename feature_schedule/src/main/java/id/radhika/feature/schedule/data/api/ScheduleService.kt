package id.radhika.feature.schedule.data.api

import id.lesprivate.lib.data.model.BaseResponse
import id.radhika.feature.schedule.data.model.CurriculumResponseModel
import id.radhika.feature.schedule.data.model.ScheduleBodyModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
interface ScheduleService {

    @POST("schedule/create")
    suspend fun createSchedule(@Body body: ScheduleBodyModel): BaseResponse<Boolean>
}