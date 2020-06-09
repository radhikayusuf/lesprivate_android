package id.radhika.feature.user.data.impl

import id.lesprivate.lib.data.api.ApiService
import id.lesprivate.lib.data.model.SimpleResult
import id.lesprivate.lib.data.model.getExceptionResponse
import id.radhika.feature.user.data.UserRepository
import id.radhika.feature.user.data.api.UserService
import id.radhika.feature.user.data.model.LoginBodyModel
import id.radhika.feature.user.data.model.LoginResponseModel
import id.radhika.feature.user.data.model.RegisterBodyModel
import kotlinx.coroutines.delay

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 08/Jun/2020
 **/
class UserRepositoryImpl(
    private val apiService: UserService = ApiService.createService(
        UserService::class.java,
        "http://167.172.95.177:8080/"
    )
) : UserRepository {

    override suspend fun registerUser(
        fullname: String,
        username: String,
        email: String,
        password: String,
        level: String
    ): SimpleResult<String> {
        return try {
            delay(2000)
            val result = apiService.registerUser(RegisterBodyModel(email, fullname, level, password, username.toLowerCase()))
            return if (result.code in 200..205) {
                SimpleResult(true, result.data, result.data ?: result.message)
            } else {
                SimpleResult(false, result.data, result.data ?: result.message)
            }
        } catch (e: Exception) {
            getExceptionResponse<String>(e).let { SimpleResult(it.isSuccess, it.data, it.message) }
        }
    }

    override suspend fun login(email: String, password: String): SimpleResult<LoginResponseModel> {
        delay(2000)
        return try {
            val result = apiService.loginUser(LoginBodyModel(email, password))
            return if (result.code in 200..205) {
                SimpleResult(true, result.data, result.message)
            } else {
                SimpleResult(false, result.data, result.message)
            }
        } catch (e: Exception) {
            getExceptionResponse<LoginResponseModel>(e).let { SimpleResult(it.isSuccess, it.data, it.message) }
        }
    }
}