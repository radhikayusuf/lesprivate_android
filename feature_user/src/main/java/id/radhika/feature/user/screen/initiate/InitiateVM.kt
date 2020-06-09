package id.radhika.feature.user.screen.initiate

import id.lesprivate.lib.mvvm.BaseVM

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 08/Jun/2020
 **/
class InitiateVM(
    private val contentDao: InitiateDao = InitiateDao()
) : BaseVM<InitiateDao>(contentDao) {

    override suspend fun onCreate() {

    }
}