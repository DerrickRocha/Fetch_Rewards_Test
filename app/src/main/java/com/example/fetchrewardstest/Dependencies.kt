package com.example.fetchrewardstest

import com.example.fetchrewardstest.interactors.HomeInteractor
import com.example.fetchrewardstest.interactors.RealHomeInteractor
import com.example.fetchrewardstest.network.FetchApiService
import com.example.fetchrewardstest.repositories.RealHomeRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Dependencies {
    private var initialized = false
    lateinit var homeInteractor: HomeInteractor

    fun initialize() {
        if (!initialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val api = retrofit.create(FetchApiService::class.java)
            val repository = RealHomeRepository(api)
            homeInteractor = RealHomeInteractor(repository)
            initialized = true
        }
    }
}