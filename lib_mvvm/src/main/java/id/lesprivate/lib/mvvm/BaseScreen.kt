package id.lesprivate.lib.mvvm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import id.lesprivate.lib.mvvm.util.showToast
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Mar/2020
 **/
abstract class BaseScreen<B : ViewBinding, VM : BaseVM<D>, D : BaseDao>(
    private val viewBinder: (LayoutInflater) -> ViewBinding
) : Fragment(), CoroutineScope {

    val activity by lazy { requireActivity() as BaseActivity }
    private val job = SupervisorJob()
    private var isHasCreated = false
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun destroy() = coroutineContext.cancelChildren()

    val binding by lazy { viewBinder.invoke(LayoutInflater.from(requireContext())) as B }
    val vm: VM by lazy {
        ViewModelProvider(viewModelStore, defaultViewModelProviderFactory).get(
            getViewModel()
        )
    }

    abstract fun onViewReady()

    abstract fun render(): Renderer<D>

    abstract fun getViewModel(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launch {
            vm.registerObserverToRender(this@BaseScreen)
            if (!isHasCreated) {
                vm.onCreate()
                isHasCreated = true
            }
        }
    }

    override fun onResume() {
        super.onResume()
        vm.registerToast(this) { msg, resMsg ->
            if (msg.isNotEmpty()) showToast(msg)
            else if (resMsg != -1) showToast(resMsg)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        vm.unRegisterObserver(this@BaseScreen)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewReady()
    }

    override fun onPause() {
        super.onPause()
        destroy()
    }

    open fun onReceivedData(dataResult: Bundle?) {}

    fun onBackPressed() {}

    fun openScreen(screen: BaseScreen<*, *, *>, extras: Bundle? = null) {
        activity.replaceScreen(screen, extras = extras)
    }

    fun openFreshScreen(screen: BaseScreen<*, *, *>, extras: Bundle? = null) {
        activity.replaceScreen(screen, false, extras)
    }

    fun finishScreen(extras: Bundle? = null) {
        activity.finishScreen(extras)
    }

    fun openActivity(from: Context, clazz: Class<*>){
        startActivity(Intent(from, clazz))
    }
}

typealias Renderer<D> = D.() -> Unit