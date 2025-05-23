package me.albinmathew.photoapp.data.repository

import me.albinmathew.photoapp.app.api.BaseResponseDo

interface PhotoRepository {
    suspend fun searchPhotos(query: String): BaseResponseDo // Or a more domain-specific model if we were to map it
}
