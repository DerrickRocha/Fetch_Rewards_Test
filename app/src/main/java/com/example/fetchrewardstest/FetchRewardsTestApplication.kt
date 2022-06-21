package com.example.fetchrewardstest

import android.app.Application

class FetchRewardsTestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Dependencies.initialize()
    }
}