package id.radhika.feature.schedule.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
data class UserResponseModel(
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("fullname")
    val fullname: String? = null,
    @SerializedName("id_user")
    val idUser: Int? = null,
    @SerializedName("is_admin")
    val isAdmin: Int? = null,
    @SerializedName("level")
    val level: String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("phone_number")
    val phoneNumber: String? = null,
    @SerializedName("username")
    val username: String? = null

)