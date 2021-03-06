package com.nowiwr01.stop_smoking.model.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nowiwr01.stop_smoking.R
import com.nowiwr01.domain.model.user.smoke_info.Star

class StarAdapter(
    private val stars: List<Star>
): RecyclerView.Adapter<StarAdapter.StarViewHolder>() {

    override fun getItemCount() = stars.size

    override fun onBindViewHolder(holder: StarViewHolder, position: Int) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StarViewHolder(
        LayoutInflater.from(parent.context).inflate(viewType, parent, false)
    )

    override fun getItemViewType(position: Int): Int {
        return when (stars[position].size) {
            1 -> R.layout.item_star_days
            2 -> R.layout.item_star_weeks
            3 -> R.layout.item_star_month_under_6
            4 -> R.layout.item_star_month_above_6
            5 -> R.layout.item_star_years
            else -> throw IllegalStateException("Wrong size of the star.")
        }
    }

    class StarViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}