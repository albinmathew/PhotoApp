package me.albinmathew.photoapp.di

import android.app.Application
import android.content.Context
import dagger.Component
import me.albinmathew.photoapp.app.ui.PhotosActivity


@ApplicationScope
@Component(modules = [ApplicationModule::class, PhotosModule::class])
interface ApplicationComponent {

    @ApplicationContext
    fun context(): Context

    fun inject(app: Application)

    fun inject(activity: PhotosActivity)
}