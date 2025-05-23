package me.albinmathew.photoapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.albinmathew.photoapp.app.api.FlickerApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideFlickerApi(): FlickerApi {
        return FlickerApi.create() // Assuming FlickerApi.create() is how it's instantiated
    }
}
