package com.konkuk.ottae.bus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.konkuk.ottae.R

class BusAdapter(private val items: List<BusItem>) :
    RecyclerView.Adapter<BusAdapter.BusViewHolder>() {

    inner class BusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtRouteName: TextView = itemView.findViewById(R.id.txtRouteName)
        val txtArrivalInfo: TextView = itemView.findViewById(R.id.txtArrivalInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bus_arrival, parent, false)
        return BusViewHolder(view)
    }

    override fun onBindViewHolder(holder: BusViewHolder, position: Int) {
        val item = items[position]
        holder.txtRouteName.text = item.routeName
        holder.txtArrivalInfo.text = item.arrivalInfo
    }

    override fun getItemCount(): Int = items.size
}
