package com.eyup.gurel.lib.adapter.monster

import android.view.View
import android.view.ViewGroup
import com.eyup.gurel.lib.adapter.monster.adapter.RecyclerDataSource
import com.eyup.gurel.lib.adapter.monster.item.ItemRenderer
import com.eyup.gurel.lib.adapter.monster.item.RecyclerItem
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.HashMap

class RecyclerDataSourceTest {
    private val rendererOne: ItemRenderer<out RecyclerItem> = TestRenderer(1)
    private val rendererTwo: ItemRenderer<out RecyclerItem> = TestRenderer(2)
    private val itemOne = TestItem(1, "r1")
    private val itemTwo = TestItem(2, "r1")
    private val itemThree = TestItem(3, "r2")
    private lateinit var dataSource: RecyclerDataSource
    @BeforeEach
    fun setUp() {
        val items = listOf<RecyclerItem>(itemOne, itemTwo, itemThree)
        val renderers: MutableMap<String, ItemRenderer<out RecyclerItem>> = HashMap()
        renderers["r1"] = rendererOne
        renderers["r2"] = rendererTwo
        dataSource = RecyclerDataSource(renderers)
        dataSource.seedData(items)
    }

    @Test
    fun rendererForType() {
        Assertions.assertEquals(rendererOne, dataSource.rendererForType(rendererOne.layoutRes()))
        Assertions.assertEquals(rendererTwo, dataSource.rendererForType(rendererTwo.layoutRes()))
    }

    @Test
    fun viewResourceForPosition() {
        Assertions.assertEquals(rendererOne.layoutRes(), 1)
        Assertions.assertEquals(rendererTwo.layoutRes(), 2)
    }


    @Test
    fun getCount() {
        Assertions.assertEquals(3, dataSource.count)
    }

    @Test
    fun getItem() {
        Assertions.assertEquals(itemOne, dataSource.getItem(0))
        Assertions.assertEquals(itemThree, dataSource.getItem(2))
    }

    internal class TestItem(override val id: Long, private val key: String) : RecyclerItem {
        override fun renderKey(): String {
            return key
        }
    }

    internal class TestRenderer(private val layoutRes: Int) : ItemRenderer<RecyclerItem> {
        override fun layoutRes(): Int {
            return layoutRes
        }
        override fun createView(parent: ViewGroup): View {
            TODO("Not yet implemented")
        }
        override fun render(itemView: View, item: RecyclerItem) {}
    }
}