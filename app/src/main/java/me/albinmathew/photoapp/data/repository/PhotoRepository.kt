package me.albinmathew.photoapp.data.repository

import me.albinmathew.photoapp.domain.model.Photo

interface PhotoRepository {
    suspend fun searchPhotos(query: String): List<Photo>
}
