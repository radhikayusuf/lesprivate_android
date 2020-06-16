package id.radhika.feature.schedule.data.api

import id.lesprivate.lib.data.model.BaseResponse
import id.radhika.feature.schedule.data.model.CurriculumResponseModel
import retrofit2.http.GET

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
interface CurriculumService {

    @GET("curriculum")
    suspend fun getCurriculum(): BaseResponse<List<CurriculumResponseModel>>
}