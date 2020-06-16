package id.radhika.feature.schedule.screen.detailform

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import id.lesprivate.lib.mvvm.BaseScreen
import id.lesprivate.lib.mvvm.Renderer
import id.lesprivate.lib.ui.utils.EditTextHelper
import id.lesprivate.lib.ui.utils.toStringDate
import id.lesprivate.lib.ui.utils.toStringTime
import id.radhika.feature.schedule.databinding.ScreenDetailFormBinding
import java.util.*


class DetailFormScreen :
    BaseScreen<ScreenDetailFormBinding, DetailFormVM, DetailFormDao>(ScreenDetailFormBinding::inflate) {

    override fun onViewReady() {

        binding.fieldTanggal.setOnClickListener { showDatePicker() }
        binding.fieldTime.setOnClickListener { showTimePicker() }
        binding.buttonSave.setOnClickListener { vm.onClickSaveData(
            binding.fieldNamaLokasi.text.toString(), binding.fieldDetailLokasi.text.toString()
        ) }
        EditTextHelper.addTypingListener(binding.fieldNamaLokasi, binding.fieldDetailLokasi) { pos, text ->
            vm.onTypingField(binding.fieldNamaLokasi.text.toString(), binding.fieldDetailLokasi.text.toString())
        }
    }

    override fun render(): Renderer<DetailFormDao> = {
        renderDateTimeField(this)
        renderResult(this)
        renderValidator(this)
    }

    private fun renderValidator(dao: DetailFormDao) {
        binding.buttonSave.isEnabled = dao.isValid
    }

    private fun renderResult(dao: DetailFormDao) {
        if (dao.result != null) {
            finishScreen(dao.result)
        }
    }

    private fun renderDateTimeField(dao: DetailFormDao) {
        binding.fieldTime.isEnabled = dao.isPickTimeEnabled
        binding.fieldTime.isClickable = dao.isPickTimeEnabled

        binding.fieldTanggal.text = dao.calendar.toStringDate()
        binding.fieldTime.text = dao.calendar.toStringTime()
    }

    override fun getViewModel() = DetailFormVM::class.java

    private fun showDatePicker() {
        val calendar = Calendar.getInstance();
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        val currentMonth = calendar.get(Calendar.MONTH);
        val currentYear = calendar.get(Calendar.YEAR);

        val picker = DatePickerDialog(
            requireActivity(),
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                vm.onPickDate(year, month, dayOfMonth)
            },
            currentYear,
            currentMonth,
            currentDay
        )
        picker.datePicker.minDate = System.currentTimeMillis()
        picker.setTitle("")
        picker.show()
    }

    private fun showTimePicker() {
        val timePicker = TimePickerDialog(requireActivity(), 0,
            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                vm.onPickTime(hourOfDay, minute)
            }, 0, 0, true
        )
        timePicker.setTitle("")
        timePicker.show()
    }

    companion object {
        const val RESULT_LOCATION = "LOCATION_RESULT"
        const val RESULT_DATE_TIME = "DATE_TIME_RESULT"
        const val RESULT_LOCATION_DETAIL = "LOCATION_DETAIL_RESULT"
    }
}
