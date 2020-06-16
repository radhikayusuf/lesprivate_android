package id.radhika.feature.user.screen.initiate

import id.lesprivate.lib.mvvm.BaseVM
import id.radhika.feature.user.data.UserUseCase

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 08/Jun/2020
 **/
class InitiateVM(
    private val contentDao: InitiateDao = InitiateDao(),
    private val useCase: UserUseCase = UserUseCase()
) : BaseVM<InitiateDao>(contentDao) {

    override suspend fun onCreate() {
        contentDao.isLogin = useCase.isUserLogin()
    }
}