package com.eyup.gurel.lib.adapter.monster.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eyup.gurel.lib.adapter.monster.item.RecyclerItem

class RecyclerDiffCallback internal constructor(private val oldList: List<RecyclerItem>,
                                                private val newList: List<RecyclerItem>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}