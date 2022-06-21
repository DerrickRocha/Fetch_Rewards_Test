package com.example.fetchrewardstest.extensions

import com.example.fetchrewardstest.models.Item

fun List<Item>.sortByListIdThenByName(): List<Item> = sortedBy { it.listId }.sortByName()

fun List<Item>.sortByListId(): List<Item> = sortedBy { it.listId }

fun List<Item>.sortByName(): List<Item> {
    return emptyList()
}
