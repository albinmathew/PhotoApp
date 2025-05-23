package me.albinmathew.photoapp.data.repository

import me.albinmathew.photoapp.app.api.FlickerApi
import me.albinmathew.photoapp.app.api.ImageData // Required for mapping
import me.albinmathew.photoapp.domain.model.Photo
import javax.inject.Inject // Hilt injection

class PhotoRepositoryImpl @Inject constructor(
    private val flickerApi: FlickerApi
) : PhotoRepository {

    override suspend fun searchPhotos(query: String): List<Photo> {
        val response = flickerApi.search(query)
        return response.photos?.photo?.mapNotNull { imageData ->
            // Assuming 'id' and 'url_s' are key fields. Handle nulls if necessary.
            // Title can be nullable or default to empty.
            if (imageData.id != null && imageData.url_s != null) {
                Photo(
                    id = imageData.id,
                    title = imageData.title ?: "Untitled",
                    url = imageData.url_s
                )
            } else {
                null // Or handle error / filter out invalid items
            }
        } ?: emptyList() // Return empty list if photos or photo list is null
    }
}
