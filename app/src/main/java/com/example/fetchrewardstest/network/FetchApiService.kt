package com.example.fetchrewardstest.network

import com.example.fetchrewardstest.models.Item
import retrofit2.http.GET

interface FetchApiService {
    @GET("hiring.json")
    fun getList(): List<Item>
}