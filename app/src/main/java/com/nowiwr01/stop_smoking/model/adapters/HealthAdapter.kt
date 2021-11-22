package com.nowiwr01.stop_smoking.model.adapters

import android.animation.ValueAnimator
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

    override fun onBindViewHolder(holder: HealthViewHolder, position: Int) = with(holder) {
        bindCommon(list[position])
       if (position <= 2 && map[position] == null) {
           bindWithAnimation(list[position])
       } else {
           bindWithoutAnimation(list[position])
       }
    }

    override fun getItemCount() = list.size

    override fun onViewRecycled(holder: HealthViewHolder) {
        map[holder.absoluteAdapterPosition] = holder.viewBinding.healthProgress.progress
    }

    class HealthViewHolder(private val time: Long, itemView: View): RecyclerView.ViewHolder(itemView) {

        val viewBinding by viewBinding<ItemHealthBinding>()

        fun bindCommon(health: HealthItem) {
            viewBinding.title.text = health.title
            viewBinding.description.text = health.description
        }

        fun bindWithAnimation(health: HealthItem) {
            val curProgress = if (time >= health.progress) 1f else time.toFloat() / health.progress
            ValueAnimator.ofFloat(0f, curProgress).apply {
                duration = 1000L
                addUpdateListener {
                    val cnt = (it.animatedValue as Float * 100).toInt()
                    viewBinding.healthProgressText.text = String.format("%s%%", cnt)
                }
            }.run {
                start()
            }
            viewBinding.healthProgress.setProgressWithAnimation(curProgress, 1000L)
        }

        fun bindWithoutAnimation(health: HealthItem) {
            val curProgress = if (time >= health.progress) 1f else time.toFloat() / health.progress
            viewBinding.healthProgress.progress = curProgress
            viewBinding.healthProgressText.text = String.format("%s%%", (curProgress * 100).toInt())
        }
    }
}