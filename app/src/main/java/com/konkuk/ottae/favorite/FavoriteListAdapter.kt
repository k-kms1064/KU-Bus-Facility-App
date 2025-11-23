package com.konkuk.ottae.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.konkuk.ottae.R

class FavoriteListAdapter(
    private var items: List<FavoriteEntity>,
    private val viewModel: FavoriteViewModel
) : RecyclerView.Adapter<FavoriteListAdapter.FavoriteViewHolder>() {

    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.facilityNameText)
        val favoriteIcon: ImageView = itemView.findViewById(R.id.favoriteIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.facility_item, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = items[position]

        holder.nameText.text = item.facilityName
        holder.favoriteIcon.setImageResource(R.drawable.ic_favorite)

        holder.favoriteIcon.setOnClickListener {
            viewModel.removeFavorite(item)

            val newList = items.toMutableList()
            newList.remove(item)
            updateList(newList)
        }

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateList(newList: List<FavoriteEntity>) {
        this.items = newList
        notifyDataSetChanged()
    }
}
