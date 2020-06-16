package id.radhika.feature.schedule.screen.lesform

import android.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import id.lesprivate.lib.mvvm.BaseScreen
import id.lesprivate.lib.mvvm.Renderer
import id.radhika.feature.schedule.databinding.ScreenLesFormBinding
import id.radhika.feature.schedule.screen.detailform.DetailFormScreen
import id.radhika.feature.schedule.screen.detailform.DetailFormScreen.Companion.RESULT_DATE_TIME
import id.radhika.feature.schedule.screen.detailform.DetailFormScreen.Companion.RESULT_LOCATION
import id.radhika.feature.schedule.screen.detailform.DetailFormScreen.Companion.RESULT_LOCATION_DETAIL

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
class LesFormScreen :
    BaseScreen<ScreenLesFormBinding, LesFormVM, LesFormDao>(ScreenLesFormBinding::inflate),
    AdapterView.OnItemSelectedListener {

    private val scheduleAdapter by lazy { LesFormAdapter() }
    private val spinnerAdapter by lazy {
        ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_dropdown_item,
            mutableListOf<String>()
        )
    }

    override fun onViewReady() {
        binding.recyclerCurriculum.adapter = scheduleAdapter
        binding.recyclerCurriculum.layoutManager = LinearLayoutManager(requireContext())
        binding.spinnerCurriculum.adapter = spinnerAdapter
        binding.spinnerCurriculum.onItemSelectedListener = this@LesFormScreen
        scheduleAdapter.onClickItem = { pos -> vm.removeSelectedMeet(pos) }
        binding.buttonAddClass.setOnClickListener { openScreen(DetailFormScreen()) }
        binding.buttonSubmit.setOnClickListener { vm.onClickSimpan(binding.spinnerCurriculum.selectedItemPosition) }
    }

    override fun render(): Renderer<LesFormDao> = {
        renderSchedule(this)
        renderValidator(this)
        renderSpinner(this)
        renderInformation(this)
        renderLoading(this)
        if (isCompleted) {
            activity.finish()
        }
    }

    private fun renderLoading(dao: LesFormDao) {
        if (dao.isLoading) {
            binding.buttonSubmit.setOnClickListener(null)
        } else {
            binding.buttonSubmit.setOnClickListener { vm.onClickSimpan(binding.spinnerCurriculum.selectedItemPosition) }
        }
        binding.spinnerCurriculum.isEnabled = !dao.isLoading
        binding.buttonAddClass.isEnabled = !dao.isLoading
        binding.buttonProgress.visibility = if (dao.isLoading) View.VISIBLE else View.GONE
        binding.buttonText.visibility = if (!dao.isLoading) View.VISIBLE else View.GONE
    }

    private fun renderInformation(dao: LesFormDao) {
        binding.lesCost.text =
            "Les Bahasa Inggris - ${dao.selectedCurriculum?.curriculum.orEmpty()}"
        binding.jumlahPertemuan.text = "${dao.schedules.size} Kali"
    }

    private fun renderValidator(dao: LesFormDao) {
        binding.buttonSubmit.isEnabled = dao.isValid
    }

    private fun renderSchedule(dao: LesFormDao) {
        scheduleAdapter.data.clear()
        scheduleAdapter.data.addAll(dao.schedules)
        scheduleAdapter.notifyDataSetChanged()
    }

    private fun renderSpinner(dao: LesFormDao) {
        if (dao.curriculum.isNotEmpty()) {
            spinnerAdapter.let { adp ->
                adp.clear()
                adp.addAll(dao.curriculum.map { it.curriculum.orEmpty() })
                adp.notifyDataSetChanged()
            }
        }
    }

    override fun onReceivedData(dataResult: Bundle?) {
        super.onReceivedData(dataResult)
        if (dataResult != null) {
            val location = dataResult.getString(RESULT_LOCATION).orEmpty()
            val locationDetail = dataResult.getString(RESULT_LOCATION_DETAIL).orEmpty()
            val dateTime = dataResult.getLong(RESULT_DATE_TIME, 0L)
            val curriculum = vm.getCurriculum(binding.spinnerCurriculum.selectedItemPosition)
            vm.addMeetData(curriculum.curriculum.orEmpty(), location, dateTime, locationDetail)
        }
    }

    override fun getViewModel() = LesFormVM::class.java

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        vm.changeMeetTitle(position)
    }
}