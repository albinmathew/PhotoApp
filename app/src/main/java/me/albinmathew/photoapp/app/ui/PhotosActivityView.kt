package me.albinmathew.photoapp.app.ui

import me.albinmathew.photoapp.app.api.ImageData
import me.albinmathew.photoapp.app.base.BasePresenterView

interface PhotosActivityView : BasePresenterView {

    fun onPhotosFetched(lisOfImages: List<ImageData>)

}