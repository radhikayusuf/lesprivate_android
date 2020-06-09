package id.radhika.feature.user.data.model

import com.google.gson.annotations.SerializedName

data class RegisterBodyModel(
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("fullname")
    val fullname: String? = null,
    @SerializedName("level")
    val level: String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("username")
    val username: String? = null
)