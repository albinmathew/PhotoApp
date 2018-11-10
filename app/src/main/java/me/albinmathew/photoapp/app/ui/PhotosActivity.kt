package me.albinmathew.photoapp.app.ui

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_photos.*
import me.albinmathew.photoapp.PhotoApp

import me.albinmathew.photoapp.R
import me.albinmathew.photoapp.app.api.ImageData
import me.albinmathew.photoapp.app.base.BaseActivity

class PhotosActivity : BaseActivity<PhotosActivityView, PhotosActivityPresenter>(), PhotosActivityView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PhotoApp.graph.inject(this)
    }

    override fun initViews() {
        super.initViews()
        btnSearch.setOnClickListener { presenter.searchPhotos(etSearchBox.text.toString()) }
    }

    override fun getLayout(): Int = R.layout.activity_photos

    override fun onPhotosFetched(lisOfImages: List<ImageData>) {
        rvPhotosList.layoutManager = GridLayoutManager(this, 3)
        rvPhotosList.adapter = PhotosAdapter(lisOfImages)
    }

    override fun showProgress() {
        progressCircular.visibility = View.VISIBLE
    }

    override fun dismissProgress() {
        progressCircular.visibility = View.GONE
    }

}
