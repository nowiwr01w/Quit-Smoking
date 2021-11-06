package com.nowiwr01.stop_smoking.model.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nowiwr01.stop_smoking.R
import org.joda.money.CurrencyUnit

class CurrenciesAdapter(
    private val list: List<CurrencyUnit>,
    private val callback: (CurrencyUnit) -> Unit
): RecyclerView.Adapter<CurrenciesAdapter.CurrenciesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder{
        val holder = CurrenciesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        )
        holder.itemView.setOnClickListener {
            callback.invoke(list[holder.absoluteAdapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    class CurrenciesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(currency: CurrencyUnit) {
            itemView.findViewById<TextView>(R.id.currency_text).text = currency.symbol
        }
    }
}