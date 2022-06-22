package com.example.fetchrewardstest.extensions

import com.example.fetchrewardstest.models.Item

fun List<Item>.sortByListIdThenByName(): List<Item> {
    return sortedWith(
            compareBy(
                { it.listId },
                {
                    // Extract integer value from name String.
                    val numberFromName = it.name?.filter { name -> name.isDigit() }
                    if (numberFromName.isNullOrBlank()) {
                        // Use 0 for the value if there is no number found in the String.
                        0
                    } else {
                        numberFromName.toInt()
                    }
                }
            )
        )
}

fun List<Item>.filterOutBlankAndNullNames(): List<Item> = filter { !it.name.isNullOrBlank() }
