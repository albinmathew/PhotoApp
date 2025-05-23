package me.albinmathew.photoapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PhotoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // Other app-level setup can go here if needed
    }
}