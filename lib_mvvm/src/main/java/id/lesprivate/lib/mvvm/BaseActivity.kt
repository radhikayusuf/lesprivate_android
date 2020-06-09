package id.lesprivate.lib.mvvm

import androidx.appcompat.app.AppCompatActivity
import id.lesprivate.lib.mvvm.util.replaceScreen
import java.util.*

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 08/Jun/2020
 **/
abstract class BaseActivity : AppCompatActivity() {

    private var currentScreen: BaseScreen<*, *, *>? = null
    private val listOfScreen = Stack<BaseScreen<*, *, *>>()

    abstract fun frameLayoutId(): Int

    override fun onBackPressed() {
        if (listOfScreen.isNotEmpty()) {
            listOfScreen.pop()
            if (listOfScreen.lastOrNull() != null) {
                supportFragmentManager.replaceScreen(
                    listOfScreen.last(),
                    frameLayoutId(),
                    android.R.animator.fade_in,
                    android.R.animator.fade_out
                )
            } else {
                currentScreen?.onBackPressed()
                super.onBackPressed()
            }
        } else {
            currentScreen?.onBackPressed()
            super.onBackPressed()
        }
    }

    fun replaceScreen(screen: BaseScreen<*, *, *>, stack: Boolean = true) {
        if (stack) {
            listOfScreen.add(screen)
        } else {
            listOfScreen.clear()
        }
        currentScreen = screen
        supportFragmentManager.replaceScreen(screen, frameLayoutId(), android.R.animator.fade_in, android.R.animator.fade_out)
    }

    fun finishScreen() {
        if (listOfScreen.isNotEmpty()) {
            listOfScreen.pop().let {
                currentScreen = it
                supportFragmentManager.replaceScreen(it, frameLayoutId(), android.R.animator.fade_in, android.R.animator.fade_out)
            }
        } else {
            finish()
        }
    }
}