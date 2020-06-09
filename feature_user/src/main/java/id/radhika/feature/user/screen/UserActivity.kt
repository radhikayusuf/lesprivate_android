package id.radhika.feature.user.screen

import android.os.Bundle
import id.lesprivate.lib.mvvm.BaseActivity
import id.radhika.feature.user.R
import id.radhika.feature.user.screen.initiate.InitiateScreen
import id.radhika.feature.user.screen.login.LoginScreen
import id.radhika.feature.user.screen.register.RegisterScreen

class UserActivity : BaseActivity() {

    private val initiateScreen by lazy { InitiateScreen() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        replaceScreen(initiateScreen)
    }

    override fun frameLayoutId() = R.id.frameUser
}
