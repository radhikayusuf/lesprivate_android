package id.radhika.feature.schedule.data

import id.lesprivate.lib.data.model.SimpleResult
import id.radhika.feature.schedule.data.impl.CurriculumRepositoryImpl
import id.radhika.feature.schedule.data.impl.ScheduleRepositoryImpl
import id.radhika.feature.schedule.data.impl.UserRepositoryImpl
import id.radhika.feature.schedule.data.model.ScheduleBodyModel
import id.radhika.feature.schedule.screen.lesform.LesFormAdapter

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
class ScheduleUseCase(
    private val userRepository: UserRepository = UserRepositoryImpl(),
    private val curriculumRepository: CurriculumRepository = CurriculumRepositoryImpl(),
    private val scheduleRepository: ScheduleRepository = ScheduleRepositoryImpl()
) {

    suspend fun getUserData() = userRepository.getUserData()

    suspend fun getCurriculum() = curriculumRepository.getCurriculum()

    suspend fun createSchedule(
        userId: Int,
        curriculumId: Int,
        schedules: ArrayList<LesFormAdapter.LesFormItem>
    ): SimpleResult<Boolean> {
        return scheduleRepository.createSchedule(ScheduleBodyModel(userId, curriculumId, schedules))
    }

}