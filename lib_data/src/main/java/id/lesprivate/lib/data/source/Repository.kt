package id.lesprivate.lib.data.source

import id.lesprivate.lib.data.model.BaseResponse

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 04/Apr/2020
 **/
abstract class Repository<T> {

    abstract suspend fun fetchData(): BaseResponse<T>

}