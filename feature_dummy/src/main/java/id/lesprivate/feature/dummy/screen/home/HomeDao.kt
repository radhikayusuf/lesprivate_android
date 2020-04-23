package id.lesprivate.feature.dummy.screen.home

import id.lesprivate.lib.mvvm.BaseDao
import id.lesprivate.lib.mvvm.util.LiveDao
import id.lesprivate.lib.mvvm.util.getValue
import id.lesprivate.lib.mvvm.util.setValue

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Mar/2020
 **/
class HomeDao : BaseDao() {

    val loadingData = LiveDao(false)
    var isLoading by loadingData::content

    val nameData = LiveDao("")
    var name by nameData::content

    val countData = LiveDao(0)
    var count by countData::content

}
