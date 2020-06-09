package id.radhika.feature.user.data

import id.lesprivate.lib.data.model.SimpleResult
import id.lesprivate.lib.data.source.UseCase
import id.radhika.feature.user.data.impl.UserRepositoryImpl
import id.radhika.feature.user.data.model.LoginResponseModel

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 22/May/2020
 **/
class UserUseCase(
    private val repository: UserRepository = UserRepositoryImpl()
) : UseCase {

    suspend fun registerUser(username: String, fullname: String, email: String, password: String, level: String): SimpleResult<String> {
        return repository.registerUser(fullname, username, email, password, level)
    }

    suspend fun login(email: String, password: String): SimpleResult<LoginResponseModel> {
        return repository.login(email, password)
    }
}