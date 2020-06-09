package id.lesprivate.lib.mvvm

import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.lesprivate.lib.ui.utils.ComponentLiveDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Mar/2020
 **/
abstract class BaseVM<D : BaseDao>(
    private val contentData: D
) : ViewModel() {

    private val toastLiveData by lazy { ComponentLiveDao("") }
    private val toastResLiveData by lazy { ComponentLiveDao(-1) }

    abstract suspend fun onCreate()

    internal fun registerObserverToRender(
        screen: BaseScreen<*, *, D>
    ) {
        getLiveDatas().forEach {
            it.observe(screen, Observer {
                screen.render().invoke(contentData)
            })
        }
    }

    internal fun unRegisterObserver(lifecycleOwner: LifecycleOwner) {
        getLiveDatas().forEach {
            it.removeObservers(lifecycleOwner)
        }
    }

    private fun getLiveDatas(): List<ComponentLiveDao<*>> {
        return try {
            contentData.javaClass.declaredFields
                .filter { ComponentLiveDao::class.java.isAssignableFrom(it.type) }
                .map { it.isAccessible = true; it.get(contentData) as ComponentLiveDao<*> }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun showToast(message: String) {
        if (message.isNotEmpty())
            toastLiveData.content = message
    }

    fun showToast(@StringRes messageRes: Int) {
        if (messageRes != -1)
            toastResLiveData.content = messageRes
    }

    fun registerToast(lifecycleOwner: LifecycleOwner, observer: (String, Int) -> Unit) {
        toastLiveData.observe(lifecycleOwner, Observer { observer(it, -1) })
        toastResLiveData.observe(lifecycleOwner, Observer { observer("", it) })
    }

    fun unRegisterMessage(lifecycleOwner: LifecycleOwner) {
        toastLiveData.removeObservers(lifecycleOwner)
        toastResLiveData.removeObservers(lifecycleOwner)
    }

    fun launch(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        command: suspend () -> Unit
    ) =
        viewModelScope.launch(dispatcher) {
            command.invoke()
        }

    fun <T> async(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        command: suspend () -> T
    ) =
        viewModelScope.async(dispatcher) {
            command.invoke()
        }
}