package me.albinmathew.photoapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.albinmathew.photoapp.data.repository.PhotoRepository
import me.albinmathew.photoapp.data.repository.PhotoRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Or ViewModelComponent if a shorter lifecycle is desired
abstract class RepositoryModule {

    @Binds
    @Singleton // Ensure only one instance of the repository
    abstract fun bindPhotoRepository(
        photoRepositoryImpl: PhotoRepositoryImpl
    ): PhotoRepository
}
