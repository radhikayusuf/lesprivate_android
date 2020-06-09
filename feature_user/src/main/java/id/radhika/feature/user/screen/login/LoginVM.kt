package id.radhika.feature.user.screen.login

import androidx.lifecycle.viewModelScope
import id.lesprivate.lib.mvvm.BaseVM
import id.lesprivate.lib.mvvm.util.isEmail
import id.lesprivate.lib.mvvm.util.isPassword
import id.lesprivate.lib.mvvm.util.isUsername
import id.radhika.feature.user.R
import id.radhika.feature.user.data.UserUseCase
import kotlinx.coroutines.launch

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 07/Jun/2020
 **/
class LoginVM(
    private val dao: LoginDao = LoginDao(),
    private val userUseCase: UserUseCase = UserUseCase()
) : BaseVM<LoginDao>(dao) {

    override suspend fun onCreate() {

    }

    fun onClickLogin(email: String, password: String) {
        viewModelScope.launch {
            dao.isLoading = true
            userUseCase.login(email, password).let { result ->
                if (result.isSuccess)
                    dao.isValid = true
                else
                    showToast(result.message)
            }
            dao.isLoading = false
        }
    }

    fun validateFields(pos: Int, email: String, password: String) {
        resetFieldsData()

        if (email.isEmpty() && pos == 0)
            dao.errorEmail = R.string.text_harap_isi_email
        else if (!email.isEmail() && pos == 0)
            dao.errorEmail = R.string.text_format_email_tidak_benar

        if (password.isEmpty() && pos == 1)
            dao.errorPassword = R.string.text_harap_isi_password
        else if (!password.isPassword() && pos == 1)
            dao.errorPassword = R.string.text_format_kata_sandi_tidak_benar

        dao.isAnyError = !isValid()
    }

    private fun isValid() = dao.errorEmail == null && dao.errorPassword == null

    private fun resetFieldsData() {
        dao.apply {
            isValid = false
            errorEmail = null
            errorPassword = null
        }
    }
}