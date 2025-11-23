package com.konkuk.ottae.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.konkuk.ottae.R

class FavoriteListAdapter(
    private val items: List<FavoriteEntity> = emptyList(),
    private val onItemClick: (FavoriteEntity) -> Unit = {}
) : RecyclerView.Adapter<FavoriteListAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtFacilityName)
        val txtCategory: TextView = itemView.findViewById(R.id.txtFacilityCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.facility_item, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = items[position]
        holder.txtName.text = item.name
        holder.txtCategory.text = item.category

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = items.size
}
