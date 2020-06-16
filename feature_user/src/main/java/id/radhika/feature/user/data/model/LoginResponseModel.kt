package id.radhika.feature.user.data.model


import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
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