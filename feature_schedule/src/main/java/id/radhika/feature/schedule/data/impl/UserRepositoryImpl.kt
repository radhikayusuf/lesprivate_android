package id.radhika.feature.schedule.data.impl

import android.content.SharedPreferences
import com.google.gson.Gson
import id.radhika.feature.schedule.FeatureScheduleModule
import id.radhika.feature.schedule.data.UserRepository
import id.radhika.feature.schedule.data.model.UserResponseModel
import id.lesprivate.lib.data.pref.string

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
class UserRepositoryImpl(
    private val gson: Gson = Gson(),
    pref: SharedPreferences = FeatureScheduleModule.get().createPref()
) : UserRepository {

    var userStringData: String by pref.string(USER_DATA_KEY, "")

    override suspend fun getUserData(): UserResponseModel? {
        return try {
            gson.fromJson<UserResponseModel>(userStringData, UserResponseModel::class.java)
        } catch (e: Exception) { null }
    }

    companion object {
        const val USER_DATA_KEY = "USER_DATA_KEY_PREF"
    }
}