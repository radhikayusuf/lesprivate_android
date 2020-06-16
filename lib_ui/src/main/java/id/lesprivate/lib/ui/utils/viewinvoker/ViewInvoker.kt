package id.lesprivate.lib.ui.utils.viewinvoker

import android.view.View
import id.lesprivate.lib.ui.utils.ComponentLiveDao

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 22/May/2020
 **/
interface ViewInvoker {

    fun attach(data: ComponentLiveDao<*>)

    fun detach()

    companion object {

        fun create(v: View) {

        }
    }
}