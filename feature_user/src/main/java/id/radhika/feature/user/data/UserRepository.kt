package id.radhika.feature.user.data

import id.lesprivate.lib.data.model.SimpleResult
import id.radhika.feature.user.data.model.LoginResponseModel

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 08/Jun/2020
 **/
interface UserRepository {

    suspend fun registerUser(fullname: String, username: String, email: String, password: String, level: String): SimpleResult<String>

    suspend fun login(email: String, password: String): SimpleResult<LoginResponseModel>

    suspend fun isLogin(): Boolean

    suspend fun logout()
}