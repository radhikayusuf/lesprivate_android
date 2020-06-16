package id.radhika.feature.schedule.screen.lesform

import id.lesprivate.lib.mvvm.BaseDao
import id.lesprivate.lib.ui.utils.*
import id.radhika.feature.schedule.data.model.CurriculumResponseModel

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
class LesFormDao : BaseDao() {

    val schedules = LiveListDao<LesFormAdapter.LesFormItem>()
    val curriculum = LiveListDao<CurriculumResponseModel>()

    private val isValidData = ComponentLiveDao(false)
    var isValid by isValidData::content

    private val isLoadingData = ComponentLiveDao(false)
    var isLoading by isLoadingData::content

    private val isCompletedData = ComponentLiveDao(false)
    var isCompleted by isCompletedData::content

    private val curriculumData = NullableLiveDao<CurriculumResponseModel>(null)
    var selectedCurriculum by curriculumData::content
}