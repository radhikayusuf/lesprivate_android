package id.radhika.feature.user.data.model


import com.google.gson.annotations.SerializedName

data class LoginBodyModel(
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("username")
    val username: String? = null
)