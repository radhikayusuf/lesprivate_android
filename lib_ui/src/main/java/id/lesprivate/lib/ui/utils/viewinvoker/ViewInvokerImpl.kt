package id.lesprivate.lib.ui.utils.viewinvoker

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import id.lesprivate.lib.ui.utils.ComponentLiveDao

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 22/May/2020
 **/
class ViewInvokerImpl(
    private val view: View
) : ViewInvoker {

    private val lifecycle = object : Lifecycle() {
        override fun addObserver(observer: LifecycleObserver) {

        }

        override fun removeObserver(observer: LifecycleObserver) {

        }

        override fun getCurrentState(): State = State.DESTROYED
//            when {
//                view.isD -> {
//                }
//                else -> {
//                }
//            }
//        }

    }

    override fun attach(data: ComponentLiveDao<*>) {
        data.observe({ lifecycle }, {

        })
    }

    override fun detach() {

    }
}