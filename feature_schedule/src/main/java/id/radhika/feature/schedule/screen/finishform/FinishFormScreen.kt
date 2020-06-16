package id.radhika.feature.schedule.screen.finishform

import id.lesprivate.lib.mvvm.BaseScreen
import id.lesprivate.lib.mvvm.Renderer
import id.radhika.feature.schedule.databinding.ScreenFinishFormBinding

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
class FinishFormScreen : BaseScreen<ScreenFinishFormBinding, FinishFormVM, FinishFormDao>(ScreenFinishFormBinding::inflate) {

    override fun onViewReady() {

    }

    override fun render(): Renderer<FinishFormDao> = {

    }

    override fun getViewModel() = FinishFormVM::class.java
}