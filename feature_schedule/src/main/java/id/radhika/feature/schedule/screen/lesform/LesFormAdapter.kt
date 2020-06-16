package id.radhika.feature.schedule.screen.lesform

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.lesprivate.lib.ui.utils.toStringDateTime
import id.radhika.feature.schedule.databinding.ItemLesFormScheduleBinding

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
class LesFormAdapter : RecyclerView.Adapter<LesFormAdapter.VH>() {

    val data = arrayListOf<LesFormItem>()
    var onClickItem: ((position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemLesFormScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.item.titleContent.text = data[position].title
        holder.item.locationContent.text = data[position].location
        holder.item.timeContent.text = data[position].time.toStringDateTime()
        holder.item.buttonRemove.setOnClickListener { onClickItem?.invoke(position) }
    }

    class VH(val item: ItemLesFormScheduleBinding) : RecyclerView.ViewHolder(item.root)

    data class LesFormItem(
        var title: String,
        val location: String,
        val time: Long,
        val locationDetail: String
    )
}