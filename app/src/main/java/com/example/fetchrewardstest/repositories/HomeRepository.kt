package com.example.fetchrewardstest.repositories

import com.example.fetchrewardstest.models.Item
import com.example.fetchrewardstest.network.FetchApiService

interface HomeRepository {
    suspend fun getItems(): List<Item>
}

class RealHomeRepository(private val service: FetchApiService): HomeRepository {
    override suspend fun getItems(): List<Item> = service.getList()
}