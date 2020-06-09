package id.radhika.feature.user.data.api

import id.lesprivate.lib.data.model.BaseResponse
import id.radhika.feature.user.data.model.LoginBodyModel
import id.radhika.feature.user.data.model.LoginResponseModel
import id.radhika.feature.user.data.model.RegisterBodyModel
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 08/Jun/2020
 **/
interface UserService {

    @POST("register")
    suspend fun registerUser(@Body registerData: RegisterBodyModel): BaseResponse<String>

    @POST("login")
    suspend fun loginUser(@Body registerData: LoginBodyModel): BaseResponse<LoginResponseModel>

}