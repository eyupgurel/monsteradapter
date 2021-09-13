package com.eyup.gurel.lib.adapter.monster.adapter

import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.eyup.gurel.lib.adapter.monster.item.ItemRenderer
import com.eyup.gurel.lib.adapter.monster.item.RecyclerItem
import java.lang.ref.WeakReference
import java.util.ArrayList
import java.util.HashMap

class RecyclerDataSource(private val renderers: Map<String, @JvmSuppressWildcards ItemRenderer<out RecyclerItem>>) {
    private val viewTypeRendererKeyMap: MutableMap<Int, String> = HashMap()
    private val data: MutableList<RecyclerItem> = ArrayList()
    private var adapterReference = WeakReference<RecyclerView.Adapter<*>>(null)

    @MainThread
    fun setData(newData: List<RecyclerItem>) {
        val diffResult = DiffUtil.calculateDiff(RecyclerDiffCallback(data, newData))
        data.clear()
        data.addAll(newData)
        val adapter = adapterReference.get()
        if (adapter != null) {
            diffResult.dispatchUpdatesTo(adapter)
        }
    }

    fun rendererForType(viewType: Int): ItemRenderer<RecyclerItem> {
        return renderers[viewTypeRendererKeyMap[viewType]] as ItemRenderer<RecyclerItem>
    }

    @LayoutRes
    fun viewResourceForPosition(position: Int): Int {
        return (renderers[data[position].renderKey()] ?: error("")).layoutRes()
    }

    val count: Int
        get() = data.size

    fun getItem(position: Int): RecyclerItem {
        return data[position]
    }

    fun attachToAdapter(adapter: RecyclerView.Adapter<*>) {
        adapterReference = WeakReference(adapter)
    }

    /**
     * Allows us to set data without invoking DiffUtil which would throw an exception during unit testing.
     * @param data
     */
    @VisibleForTesting
    fun seedData(data: List<RecyclerItem>) {
        this.data.clear()
        this.data.addAll(data)
    }

    init {
        for ((key, value) in renderers) {
            viewTypeRendererKeyMap[value.layoutRes()] = key
        }
    }
}