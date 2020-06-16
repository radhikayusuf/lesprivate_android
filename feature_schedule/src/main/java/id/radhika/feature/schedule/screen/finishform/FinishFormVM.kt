package id.radhika.feature.schedule.screen.finishform

import id.lesprivate.lib.mvvm.BaseVM

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
class FinishFormVM(
    private val contentDao: FinishFormDao = FinishFormDao()
) : BaseVM<FinishFormDao>(contentDao) {

    override suspend fun onCreate() {

    }
}