package id.radhika.feature.schedule.screen.detailform

import android.os.Bundle
import id.lesprivate.lib.mvvm.BaseDao
import id.lesprivate.lib.ui.utils.ComponentLiveDao
import id.lesprivate.lib.ui.utils.NullableLiveDao
import id.lesprivate.lib.ui.utils.getValue
import id.lesprivate.lib.ui.utils.setValue
import java.util.*

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 14/Jun/2020
 **/
class DetailFormDao : BaseDao() {
    private val isPickTimeEnabledData = ComponentLiveDao(false)
    var isPickTimeEnabled by isPickTimeEnabledData::content

    private val calendarData = ComponentLiveDao(Calendar.getInstance())
    var calendar by calendarData::content

    private val resultData = NullableLiveDao<Bundle>(null)
    var result by resultData::content

    private val isValidData = ComponentLiveDao(false)
    var isValid by isValidData::content
}