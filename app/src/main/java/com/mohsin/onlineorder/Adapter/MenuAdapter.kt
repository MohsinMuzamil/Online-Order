package com.mohsin.onlineorder.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mohsin.onlineorder.R
import com.mohsin.onlineorder.model.MenuItem

class MenuAdapter(
    menuItems: List<MenuItem>,
    private val onItemClick: (MenuItem) -> Unit // <-- add this
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    private val allItems = ArrayList(menuItems)
    private var items = ArrayList(menuItems)

    class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodImage: ImageView = view.findViewById(R.id.imgFood)
        val foodName: TextView = view.findViewById(R.id.tvFoodName)
        val foodDescription: TextView = view.findViewById(R.id.tvDescription)
        val foodPrice: TextView = view.findViewById(R.id.tvPrice)
        val dealItems: TextView = view.findViewById(R.id.tvDealItems)
        val dealBadge: TextView = view.findViewById(R.id.tvDealBadge)
    }

    fun filter(query: String) {
        items.clear()
        if (query.isEmpty()) {
            items.addAll(allItems)
        } else {
            val filterPattern = query.trim().lowercase()
            items.addAll(allItems.filter {
                it.name.lowercase().contains(filterPattern) ||
                        it.description.lowercase().contains(filterPattern) ||
                        (it.isDeal && it.dealItems.lowercase().contains(filterPattern))
            })
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu_card, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = items[position]
        holder.foodImage.setImageResource(item.imageResId)
        holder.foodName.text = item.name
        holder.foodDescription.text = item.description
        holder.foodPrice.text = item.price

        if (item.isDeal) {
            holder.dealBadge.visibility = View.VISIBLE
        } else {
            holder.dealBadge.visibility = View.GONE
        }

        if (item.isDeal && item.dealItems.isNotEmpty()) {
            holder.dealItems.visibility = View.VISIBLE
            holder.dealItems.text = "Includes: ${item.dealItems}"
        } else {
            holder.dealItems.visibility = View.GONE
        }

        holder.itemView.setOnClickListener { onItemClick(item) } // <-- set click listener
    }

    override fun getItemCount() = items.size
}