package me.albinmathew.photoapp.data.repository

import me.albinmathew.photoapp.app.api.BaseResponseDo
import me.albinmathew.photoapp.app.api.FlickerApi
import javax.inject.Inject // Hilt injection

class PhotoRepositoryImpl @Inject constructor(
    private val flickerApi: FlickerApi
) : PhotoRepository {

    override suspend fun searchPhotos(query: String): BaseResponseDo {
        // Later, we might add error handling, caching, or data mapping here
        return flickerApi.search(query)
    }
}
