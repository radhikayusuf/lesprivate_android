package id.radhika.feature.user.screen.initiate

import id.lesprivate.lib.mvvm.BaseDao
import id.lesprivate.lib.ui.utils.ComponentLiveDao
import id.lesprivate.lib.ui.utils.getValue
import id.lesprivate.lib.ui.utils.setValue

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 08/Jun/2020
 **/
class InitiateDao : BaseDao() {

    private val isLoginData = ComponentLiveDao(false)
    var isLogin by isLoginData::content
}