package id.lesprivate.feature.dummy.screen.home

import id.lesprivate.lib.mvvm.BaseDao
import id.lesprivate.lib.ui.utils.ComponentLiveDao
import id.lesprivate.lib.mvvm.util.getValue
import id.lesprivate.lib.mvvm.util.setValue

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Mar/2020
 **/
class HomeDao : BaseDao() {

    val loadingData = ComponentLiveDao(false)
    var isLoading by loadingData::content

    val nameData = ComponentLiveDao("")
    var name by nameData::content

    val countData = ComponentLiveDao(0)
    var count by countData::content

}
