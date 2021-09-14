package com.eyup.gurel.lib.adapter.monster.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter(private val dataSource: RecyclerDataSource) : RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(parent, dataSource.rendererForType(viewType))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(dataSource.getItem(position))
    }

    override fun getItemCount(): Int {
        return dataSource.count
    }

    override fun getItemViewType(position: Int): Int {
        return dataSource.viewResourceForPosition(position)
    }

    override fun getItemId(position: Int): Long {
        return dataSource.getItem(position).id
    }

    init {
        dataSource.attachToAdapter(this)
        setHasStableIds(true)
    }
}