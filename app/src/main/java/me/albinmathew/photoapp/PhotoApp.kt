package me.albinmathew.photoapp

import android.app.Application
import me.albinmathew.photoapp.di.ApplicationComponent
import me.albinmathew.photoapp.di.ApplicationModule
import me.albinmathew.photoapp.di.DaggerApplicationComponent

class PhotoApp : Application() {

    companion object {
        @JvmStatic
        lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        graph.inject(this)
    }
}