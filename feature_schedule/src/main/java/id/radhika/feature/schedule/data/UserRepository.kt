package id.radhika.feature.schedule.data

import id.radhika.feature.schedule.data.model.UserResponseModel

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 08/Jun/2020
 **/
interface UserRepository {

    suspend fun getUserData(): UserResponseModel?
}