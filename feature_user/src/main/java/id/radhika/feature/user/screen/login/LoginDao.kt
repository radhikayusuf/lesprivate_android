package id.radhika.feature.user.screen.login

import id.lesprivate.lib.mvvm.BaseDao
import id.lesprivate.lib.mvvm.util.getValue
import id.lesprivate.lib.mvvm.util.setValue
import id.lesprivate.lib.ui.utils.ComponentLiveDao
import id.lesprivate.lib.ui.utils.NullableLiveDao

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 07/Jun/2020
 **/
class LoginDao : BaseDao() {

    private val loadingData = ComponentLiveDao(false)
    var isLoading by loadingData::content

    private val isAnyErrorData = ComponentLiveDao(false)
    var isAnyError by isAnyErrorData::content

    private val validationData = ComponentLiveDao(false)
    var isValid by validationData::content

    private val errorEmailData = NullableLiveDao<Int>(null)
    var errorEmail by errorEmailData::content

    private val errorPasswordData = NullableLiveDao<Int>(null)
    var errorPassword by errorPasswordData::content
}