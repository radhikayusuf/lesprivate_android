package id.radhika.feature.schedule.data.impl

import id.lesprivate.lib.data.api.ApiService
import id.lesprivate.lib.data.model.SimpleResult
import id.lesprivate.lib.data.model.getExceptionResponse
import id.radhika.feature.schedule.FeatureScheduleModule.Companion.SCHEDULE_MODULE_BASE_URL
import id.radhika.feature.schedule.data.CurriculumRepository
import id.radhika.feature.schedule.data.api.CurriculumService
import id.radhika.feature.schedule.data.model.CurriculumResponseModel

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
class CurriculumRepositoryImpl(
    private val curriculumService: CurriculumService = ApiService.createService(CurriculumService::class.java, SCHEDULE_MODULE_BASE_URL)
) : CurriculumRepository {

    override suspend fun getCurriculum(): SimpleResult<List<CurriculumResponseModel>?> {
        return try {
            val result = curriculumService.getCurriculum()
            if (result.code in 200..205) {
                SimpleResult(true, result.data, result.message)
            } else {
                SimpleResult(false, result.data, result.message)
            }
        } catch (e: Exception) {
            getExceptionResponse<List<CurriculumResponseModel>>(e).let { SimpleResult(it.isSuccess, it.data, it.message) }
        }
    }
}