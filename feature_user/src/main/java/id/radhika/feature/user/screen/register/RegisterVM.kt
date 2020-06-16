package id.radhika.feature.user.screen.register

import androidx.lifecycle.viewModelScope
import id.lesprivate.lib.mvvm.BaseVM
import id.lesprivate.lib.mvvm.util.isEmail
import id.lesprivate.lib.mvvm.util.isPassword
import id.lesprivate.lib.mvvm.util.isUsername
import id.radhika.feature.user.R
import id.radhika.feature.user.data.UserUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 22/May/2020
 **/
class RegisterVM(
    private val dao: RegisterDao = RegisterDao(),
    private val userUseCase: UserUseCase = UserUseCase()
) : BaseVM<RegisterDao>(dao) {

    override suspend fun onCreate() {
        dao.levels = mutableListOf("SD", "SMP", "SMA/SMK", "Mahasiswa", "Karyawan", "Lainnya")
    }

    fun validateFields(
        pos: Int,
        fullname: String,
        userName: String,
        email: String,
        password: String,
        passwordAgain: String,
        levelPosition: Int
    ) {
        viewModelScope.launch {
            resetFieldsData()

            if (fullname.isEmpty() && pos == 0)
                dao.errorFullName = R.string.text_harap_isi_nama_lengkap

            if (userName.isEmpty() && pos == 1)
                dao.errorUserName = R.string.text_harap_isi_username
            else if (!userName.isUsername() && pos == 1)
                dao.errorUserName = R.string.text_username_tidak_sesuai

            if (email.isEmpty() && pos == 2)
                dao.errorEmail = R.string.text_harap_isi_email
            else if (!email.isEmail() && pos == 2)
                dao.errorEmail = R.string.text_format_email_tidak_benar

            if (password.isEmpty() && pos == 3)
                dao.errorPassword = R.string.text_harap_isi_password
            else if (!password.isPassword() && pos == 3)
                dao.errorPassword = R.string.text_format_kata_sandi_tidak_benar

            if ((passwordAgain.isEmpty() || password != passwordAgain) && pos == 4)
                dao.errorPasswordAgain = R.string.text_kata_sandi_tidak_sesui

            if (levelPosition == 0 && pos == 5)
                dao.errorLevel = R.string.text_harap_pilih_jenjang_pendidikan

            dao.isAnyError = isValid()
        }
    }

    fun onClickRegister(
        username: String,
        fullname: String,
        email: String,
        password: String,
        levelPosition: Int
    ) {
        viewModelScope.launch {
            dao.isLoading = true
            val result = userUseCase.registerUser(username, fullname, email, password, dao.levels[levelPosition])
            if (result.isSuccess) {
                showToast(result.data.orEmpty())
                delay(3000)
                dao.isValid = true
            } else {
                showToast(result.message)
            }
            dao.isLoading = false
        }
    }

    private fun isValid() =
        dao.errorFullName == null && dao.errorEmail == null && dao.errorPassword == null &&
            dao.errorPasswordAgain == null && dao.errorLevel == null && dao.errorUserName == null


    private fun resetFieldsData() {
        dao.apply {
            isValid = false
            errorUserName = null
            errorFullName = null
            errorEmail = null
            errorPassword = null
            errorPasswordAgain = null
            errorLevel = null
        }
    }
}