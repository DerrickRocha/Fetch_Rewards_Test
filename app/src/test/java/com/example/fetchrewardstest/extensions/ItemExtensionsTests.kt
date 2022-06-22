package com.example.fetchrewardstest.extensions

import com.example.fetchrewardstest.models.Item
import org.junit.Test

class ItemExtensionsTests {

    @Test
    fun filterOutBlankAndNullNames_whenListHasNullAndBlankNames_itemsWithNullAndBlankNamesShouldBeRemoved() {
        val items = listOf(
            Item(0,99, "Item 123"),
            Item(0,47, ""),
            Item(0,1, "Item 22"),
            Item(0,1, ""),
            Item(0,1, null),
            Item(0,1, "Item 4"),
            Item(0,1009, ""),
            Item(0,0, "Item 19"),
            Item(0,0, null)
        )
        val filteredItems = items.filterOutBlankAndNullNames()
        assert(filteredItems.size == 4)
        assert(items[0].name == "Item 123")
        assert(filteredItems[1].name == "Item 22")
        assert(filteredItems[2].name == "Item 4")
        assert(filteredItems[3].name == "Item 19")
    }

    @Test
    fun sortByListIdThenByName_whenListHasItems_shouldBeSortedByListIdThenName() {
        val unsorted = listOf(
            Item(0,90, "Item 275"),
            Item(0,605, "Item 72"),
            Item(0,2, "Item 5"),
            Item(0,605, "Item 0"),
            Item(0,90, null),
            Item(0,90, ""),
            Item(0,90, "Item 10099"),
            Item(0,8, "Item 5")
        )
        val sorted = unsorted.sortByListIdThenByName()
        assert(sorted[0].listId == 2 && sorted[0].name == "Item 5")
        assert(sorted[1].listId == 8 && sorted[1].name == "Item 5")
        assert(sorted[2].listId == 90 && sorted[2].name == null)
        assert(sorted[3].listId == 90 && sorted[3].name == "")
        assert(sorted[4].listId == 90 && sorted[4].name == "Item 275")
        assert(sorted[5].listId == 90 && sorted[5].name == "Item 10099")
        assert(sorted[6].listId == 605 && sorted[6].name == "Item 0")
        assert(sorted[7].listId == 605 && sorted[7].name == "Item 72")
    }
}