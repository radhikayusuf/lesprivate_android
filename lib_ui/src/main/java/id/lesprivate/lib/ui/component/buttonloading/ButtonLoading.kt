package id.lesprivate.lib.ui.component.buttonloading

import android.content.Context
import android.widget.FrameLayout
import id.lesprivate.lib.ui.component.buttonloading.ButtonLoadingDao
import id.lesprivate.lib.ui.utils.viewinvoker.ViewInvoker

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 22/May/2020
 **/
class ButtonLoading(context: Context) : FrameLayout(context) {

    private val contentData = ButtonLoadingDao()

    init { ViewInvoker.create(this) }

    fun showLoading() {

    }
}