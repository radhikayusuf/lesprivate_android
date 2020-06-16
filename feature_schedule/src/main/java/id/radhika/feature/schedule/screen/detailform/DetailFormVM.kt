package id.radhika.feature.schedule.screen.detailform

import android.os.Bundle
import android.text.Editable
import id.lesprivate.lib.mvvm.BaseVM
import id.radhika.feature.schedule.screen.detailform.DetailFormScreen.Companion.RESULT_DATE_TIME
import id.radhika.feature.schedule.screen.detailform.DetailFormScreen.Companion.RESULT_LOCATION
import id.radhika.feature.schedule.screen.detailform.DetailFormScreen.Companion.RESULT_LOCATION_DETAIL
import java.util.*

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 14/Jun/2020
 **/
class DetailFormVM(
    private val contentDao: DetailFormDao = DetailFormDao()
) : BaseVM<DetailFormDao>(contentDao) {

    override suspend fun onCreate() {

    }

    fun onPickDate(year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        contentDao.calendar = calendar
        contentDao.isPickTimeEnabled = true
    }

    fun onPickTime(hourOfDay: Int, minute: Int) {
        contentDao.calendar = contentDao.calendar.let { calendar ->
            Calendar.getInstance().apply {
                set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), hourOfDay, minute)
            }
        }
    }

    fun onClickSaveData(locationName: String, locationDetail: String) {
        val bundle = Bundle().also {
            it.putLong(RESULT_DATE_TIME, contentDao.calendar.timeInMillis)
            it.putString(RESULT_LOCATION, locationName)
            it.putString(RESULT_LOCATION_DETAIL, locationDetail)
        }

        contentDao.result = bundle
    }

    fun onTypingField(location: String, detailLocation: String) {
        contentDao.isValid = location.trim().isNotEmpty() &&
                detailLocation.trim().isNotEmpty() &&
                contentDao.isPickTimeEnabled
    }
}