package com.example.fetchrewardstest.repositories

import com.example.fetchrewardstest.models.Item

interface HomeRepository {
    suspend fun getItems(): List<Item>
}