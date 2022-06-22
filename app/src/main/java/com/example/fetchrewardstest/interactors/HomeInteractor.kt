package com.example.fetchrewardstest.interactors

import com.example.fetchrewardstest.extensions.filterOutBlankAndNullNames
import com.example.fetchrewardstest.extensions.sortByListIdThenByName
import com.example.fetchrewardstest.models.Item
import com.example.fetchrewardstest.repositories.HomeRepository

interface HomeInteractor {
    suspend fun getItems(): List<Item>
}

class RealHomeInteractor(private val repository: HomeRepository) : HomeInteractor {
    override suspend fun getItems(): List<Item> {
        return repository.getItems()
            .filterOutBlankAndNullNames()
            .sortByListIdThenByName()
    }
}