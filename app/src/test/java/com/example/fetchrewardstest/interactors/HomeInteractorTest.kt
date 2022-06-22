package com.example.fetchrewardstest.interactors

import com.example.fetchrewardstest.models.Item
import com.example.fetchrewardstest.repositories.HomeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class HomeInteractorTest {

    private val mockRepository = object : HomeRepository {
        override fun getItems(): List<Item> {
            return listOf(
                Item(0,90, "Item 275"),
                Item(0,605, "Item 72"),
                Item(0,2, "Item 5"),
                Item(0,605, "Item 0"),
                Item(0,90, null),
                Item(0,90, ""),
                Item(0,90, "Item 10099"),
                Item(0,8, "Item 57"),
                Item(0,605, null)
            )
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getItems_whenNamesNullOrBlank_itemsWithNullOrBlankValuesShouldBeRemoved() = runTest {
        val interactor = RealHomeInteractor(mockRepository)
        val items = interactor.getItems()
        assert(items.size == 6)
        for (item in items) {
            assert(!item.name.isNullOrBlank())
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getItems_whenItemsUnsorted_itemsShouldBeSortedByListIdThenName() = runTest {
        val interactor = RealHomeInteractor(mockRepository)
        val items = interactor.getItems()
        assert(items[0].listId == 2 && items[0].name == "Item 5")
        assert(items[1].listId == 8 && items[1].name == "Item 57")
        assert(items[2].listId == 90 && items[2].name == "Item 275")
        assert(items[3].listId == 90 && items[3].name == "Item 10099")
        assert(items[4].listId == 605 && items[4].name == "Item 0")
        assert(items[5].listId == 605 && items[5].name == "Item 72")
    }
}