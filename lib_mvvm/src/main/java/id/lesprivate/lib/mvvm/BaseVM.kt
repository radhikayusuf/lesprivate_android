package id.lesprivate.lib.mvvm

import androidx.annotation.StringRes
import androidx.lifecycle.*
import id.lesprivate.lib.ui.utils.ComponentLiveDao
import id.lesprivate.lib.ui.utils.LiveListDao
import id.lesprivate.lib.ui.utils.NullableLiveDao
import kotlinx.coroutines.*
import kotlinx.coroutines.launch as fire
import kotlinx.coroutines.async as await
import kotlin.coroutines.CoroutineContext

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Mar/2020
 **/
abstract class BaseVM<D : BaseDao>(
    private val contentData: D
) : ViewModel(), CoroutineScope {

    private val job: Job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

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

    private fun getLiveDatas(): List<LiveData<*>> {
        return try {
            contentData.javaClass.declaredFields
                .filter {
                    ComponentLiveDao::class.java.isAssignableFrom(it.type) ||
                            NullableLiveDao::class.java.isAssignableFrom(it.type) ||
                            LiveListDao::class.java.isAssignableFrom(it.type)
                }
                .map { it.isAccessible = true; it.get(contentData) as LiveData<*> }
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
        fire(dispatcher) {
            command.invoke()
        }

    fun <T> async(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        command: suspend () -> T
    ) =
        await(dispatcher) {
            command.invoke()
        }
}