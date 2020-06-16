package id.radhika.feature.schedule.data.model


import com.google.gson.annotations.SerializedName

data class CurriculumResponseModel(
    @SerializedName("cost")
    val cost: Int? = null,
    @SerializedName("curriculum")
    val curriculum: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id_curriculum")
    val idCurriculum: Int? = null
)