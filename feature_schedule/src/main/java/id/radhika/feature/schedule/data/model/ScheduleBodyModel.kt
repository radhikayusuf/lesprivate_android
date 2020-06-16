package id.radhika.feature.schedule.data.model

import com.google.gson.annotations.SerializedName
import id.radhika.feature.schedule.screen.lesform.LesFormAdapter

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 16/Jun/2020
 **/
data class ScheduleBodyModel(
    @SerializedName("id_user")
    val userId: Int,
    @SerializedName("id_curriculum")
    val curriculumId: Int,
    @SerializedName("schedules")
    val schedules: List<LesFormAdapter.LesFormItem>
)