package com.example.fetchrewardstest.extensions

import com.example.fetchrewardstest.models.Item

fun List<Item>.sortByListIdThenByName(): List<Item> {
    return sortedWith(
            compareBy(
                { it.listId },
                {
                    val numberFromName = it.name?.filter { name -> name.isDigit() }
                    if (numberFromName.isNullOrBlank()) {
                        0
                    } else {
                        numberFromName.toInt()
                    }
                }
            )
        )
}

fun List<Item>.filterOutBlankAndNullNames(): List<Item> = filter { !it.name.isNullOrBlank() }
