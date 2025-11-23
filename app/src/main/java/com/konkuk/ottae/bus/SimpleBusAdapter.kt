package com.konkuk.ottae.bus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.konkuk.ottae.R

class SimpleBusAdapter(
    private val items: List<SimpleBusItem> = emptyList()
) : RecyclerView.Adapter<SimpleBusAdapter.SimpleBusViewHolder>() {

    inner class SimpleBusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView = itemView.findViewById(android.R.id.text1)
        val txtValue: TextView = itemView.findViewById(android.R.id.text2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleBusViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return SimpleBusViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimpleBusViewHolder, position: Int) {
        val item = items[position]
        holder.txtTitle.text = item.title
        holder.txtValue.text = item.value
    }

    override fun getItemCount(): Int = items.size
}
