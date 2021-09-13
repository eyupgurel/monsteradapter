package com.eyup.gurel.lib.adapter.monster.item

interface RecyclerItem {
    val id: Long
    fun renderKey(): String
}