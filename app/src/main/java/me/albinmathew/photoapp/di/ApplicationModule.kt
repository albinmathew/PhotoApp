package me.albinmathew.photoapp.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides


@Module
class ApplicationModule(private val app: Application) {

    @Provides
    @ApplicationScope
    @ApplicationContext
    fun providesApplicationContext(): Context = app

}