package id.lesprivate.feature.dummy.data.repositories

import id.lesprivate.lib.data.model.BaseResponse
import kotlinx.coroutines.delay

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 04/Apr/2020
 **/
class UserRepositoryImpl : UserRepository() {

    override suspend fun fetchData(): BaseResponse<String> {
        delay(1500)
        return BaseResponse(200, "Radhika Yusuf!", "success")
    }
}