package id.radhika.feature.schedule.screen.lesform

import id.lesprivate.lib.mvvm.BaseVM
import id.lesprivate.lib.ui.utils.toStringDateTime
import id.radhika.feature.schedule.data.ScheduleUseCase
import kotlinx.coroutines.delay

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
class LesFormVM(
    private val contentDao: LesFormDao = LesFormDao(),
    private val usecCase: ScheduleUseCase = ScheduleUseCase()
) : BaseVM<LesFormDao>(contentDao) {

    override suspend fun onCreate() {
        contentDao.isLoading = true
        fetchCurriculum()
        contentDao.isLoading = false
    }

    private suspend fun fetchCurriculum() {
        usecCase.getCurriculum().let { result ->
            contentDao.curriculum.addAll(
                if (result.isSuccess) {
                    result.data.orEmpty()
                } else {
                    emptyList()
                }
            )
        }
    }


    fun addMeetData(title: String, location: String, time: Long, locationDetail: String) = launch {
        contentDao.schedules.add(
            LesFormAdapter.LesFormItem(title, location, time, locationDetail)
        )
        updateValidator()
    }

    fun removeSelectedMeet(pos: Int) = launch {
        delay(100)
        contentDao.schedules.removeAt(pos)
        updateValidator()
    }

    private fun updateValidator() {
        contentDao.isValid = contentDao.schedules.isNotEmpty()
    }

    fun getCurriculum(selectedItemPosition: Int) =
        contentDao.curriculum[selectedItemPosition]

    fun changeMeetTitle(selectedItemPosition: Int) {
        contentDao.selectedCurriculum = contentDao.curriculum[selectedItemPosition]
        contentDao.schedules.forEach {
            it.title = contentDao.curriculum[selectedItemPosition].curriculum.orEmpty()
        }
        contentDao.schedules.call()
        updateValidator()
    }

    fun onClickSimpan(position: Int) = launch {
        contentDao.isLoading = true
        val userId = usecCase.getUserData()?.idUser ?: 0
        val curriculumId = contentDao.curriculum[position].idCurriculum ?:0
        val schedules = contentDao.schedules.toArrayList()
        val result = usecCase.createSchedule(userId, curriculumId, schedules)

        if (result.isSuccess && result.data == true) {
            contentDao.isCompleted = true
        }
        contentDao.isLoading = false
    }
}