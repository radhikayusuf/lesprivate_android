package id.lesprivate.feature.dummy.screen.home

import android.view.View
import id.lesprivate.feature.dummy.databinding.FragmentHomeBinding
import id.lesprivate.lib.mvvm.BaseScreen
import id.lesprivate.lib.mvvm.Renderer

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Mar/2020
 **/
class HomeScreen : BaseScreen<FragmentHomeBinding, HomeVM, HomeDao>(FragmentHomeBinding::inflate),
    View.OnClickListener {

    override fun getViewModel() = HomeVM::class.java

    override fun onViewReady() {
        binding.buttonDec.setOnClickListener(this)
        binding.buttonInc.setOnClickListener(this)
    }

    override fun render(): Renderer<HomeDao> = {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.textContent.text = count.toString()
        binding.nameOfUser.text = name
    }

    override fun onClick(v: View?) {
        vm.calculate(v?.id == binding.buttonInc.id)
    }
}