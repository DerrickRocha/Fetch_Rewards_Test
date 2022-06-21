package com.example.fetchrewardstest.extensions

import com.example.fetchrewardstest.models.Item
import org.junit.Test

class ItemExtensionsTests {

    @Test
    fun sortByListId_whenListIdsAreDifferent_listIdsShouldBeSorted() {
        val unsorted = listOf(
            Item(0,99, ""),
            Item(0,47, ""),
            Item(0,1, ""),
            Item(0,1, ""),
            Item(0,1009, ""),
            Item(0,0, ""),
            Item(0,0, "")
        )
        val sorted = unsorted.sortByListId()
        assert(sorted[0].listId == 0)
        assert(sorted[1].listId == 0)
        assert(sorted[2].listId == 1)
        assert(sorted[3].listId == 1)
        assert(sorted[4].listId == 47)
        assert(sorted[5].listId == 99)
        assert(sorted[6].listId == 1009)
    }
}