package com.nowiwr01.stop_smoking.model.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nowiwr01.domain.model.user.smoke_info.SmokeInfo
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.stop_smoking.databinding.ItemHealthBinding
import com.nowiwr01.stop_smoking.model.health.HealthItem
import by.kirich1409.viewbindingdelegate.viewBinding

class HealthAdapter(
    private val list: List<HealthItem>,
    private val smokeInfo: SmokeInfo
): RecyclerView.Adapter<HealthAdapter.HealthViewHolder>() {

    private val map = mutableMapOf<Int, Float>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HealthViewHolder(
        System.currentTimeMillis() - smokeInfo.breakDate,
        LayoutInflater.from(parent.context).inflate(R.layout.item_health, parent, false)
    )

    override fun onBindViewHolder(holder: HealthViewHolder, position: Int) {
        holder.bind(list[position], map[position])
    }

    override fun getItemCount() = list.size

    override fun onViewRecycled(holder: HealthViewHolder) {
        map[holder.absoluteAdapterPosition] = holder.viewBinding.healthProgress.progress
    }

    class HealthViewHolder(private val time: Long, itemView: View): RecyclerView.ViewHolder(itemView) {

        val viewBinding by viewBinding<ItemHealthBinding>()

        fun bind(health: HealthItem, prevProgress: Float?) {
            with(viewBinding) {
                title.text = health.title
                description.text = health.description
                healthProgress.apply {
                    progress = 0f
                    val curProgress = if (time >= health.progress) 1f else time.toFloat() / health.progress
                    if (prevProgress != null) {
                        progress = curProgress
                    } else {
                        setProgressWithAnimation(curProgress, 1000L)
                    }
                }
            }
        }
    }
}