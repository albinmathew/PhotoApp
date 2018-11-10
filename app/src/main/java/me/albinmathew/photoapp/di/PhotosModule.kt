package me.albinmathew.photoapp.di

import dagger.Module
import dagger.Provides
import me.albinmathew.photoapp.app.ui.PhotosActivityPresenter
import me.albinmathew.photoapp.app.ui.PhotosActivityPresenterImpl


@Module
class PhotosModule {

    @Provides
    fun providesMovies(): PhotosActivityPresenter = PhotosActivityPresenterImpl()
}