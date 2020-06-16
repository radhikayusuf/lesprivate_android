package id.radhika.feature.schedule.data

import id.lesprivate.lib.data.model.SimpleResult
import id.radhika.feature.schedule.data.model.CurriculumResponseModel

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
interface CurriculumRepository {

    suspend fun getCurriculum(): SimpleResult<List<CurriculumResponseModel>?>

}