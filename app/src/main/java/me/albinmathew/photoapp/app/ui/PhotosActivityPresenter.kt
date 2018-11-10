package me.albinmathew.photoapp.app.ui

import me.albinmathew.photoapp.app.base.BasePresenter

interface PhotosActivityPresenter : BasePresenter<PhotosActivityView> {

    fun searchPhotos(query: String)

}