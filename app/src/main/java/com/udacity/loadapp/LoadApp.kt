package com.udacity.loadapp

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.udacity.loadapp.network.NetworkUtil
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class LoadApp : Application() {

    @Inject
    lateinit var networkUtil: NetworkUtil

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        networkUtil.registerNetworkCallback()
    }
}