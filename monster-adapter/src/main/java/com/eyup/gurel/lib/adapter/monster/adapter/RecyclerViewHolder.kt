package com.eyup.gurel.lib.adapter.monster.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eyup.gurel.lib.adapter.monster.item.ItemRenderer
import com.eyup.gurel.lib.adapter.monster.item.RecyclerItem

class RecyclerViewHolder(parent: ViewGroup, private val renderer: ItemRenderer<RecyclerItem>) :
    RecyclerView.ViewHolder(renderer.createView(parent)) {
    fun bind(item: RecyclerItem) {
        renderer.render(itemView, item)
    }
}