package id.radhika.feature.user.screen.register

import id.lesprivate.lib.mvvm.BaseDao
import id.lesprivate.lib.ui.utils.NullableLiveDao
import id.lesprivate.lib.ui.utils.ComponentLiveDao
import id.lesprivate.lib.mvvm.util.getValue
import id.lesprivate.lib.mvvm.util.setValue

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 22/May/2020
 **/
class RegisterDao : BaseDao() {

    private val loadingData = ComponentLiveDao(false)
    var isLoading by loadingData::content

    private val isAnyErrorData = ComponentLiveDao(false)
    var isAnyError by isAnyErrorData::content

    private val validationData = ComponentLiveDao(false)
    var isValid by validationData::content

    private val levelsData = ComponentLiveDao<List<String>>(emptyList())
    var levels by levelsData::content

    private val errorFullnameData = NullableLiveDao<Int>(null)
    var errorFullName by errorFullnameData::content

    private val errorUserNameData = NullableLiveDao<Int>(null)
    var errorUserName by errorUserNameData::content

    private val errorEmailData = NullableLiveDao<Int>(null)
    var errorEmail by errorEmailData::content

    private val errorPasswordData = NullableLiveDao<Int>(null)
    var errorPassword by errorPasswordData::content

    private val errorPasswordAgainData = NullableLiveDao<Int>(null)
    var errorPasswordAgain by errorPasswordAgainData::content

    private val errorLevelData = NullableLiveDao<Int>(null)
    var errorLevel by errorLevelData::content

}