package me.albinmathew.photoapp.app.ui

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_photos.*

import me.albinmathew.photoapp.R
import me.albinmathew.photoapp.app.api.BaseResponseDo
import me.albinmathew.photoapp.app.api.FlickerApi
import me.albinmathew.photoapp.app.api.ImageData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosActivityWithoutMvp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)
        initViews()
    }

    private fun initViews() {
        btnSearch.setOnClickListener { searchPhotos(etSearchBox.text.toString()) }
    }

    private fun onPhotosFetched(lisOfImages: List<ImageData>) {
        rvPhotosList.layoutManager = GridLayoutManager(this,4)
        rvPhotosList.adapter = PhotosAdapter(lisOfImages)
    }

    private fun searchPhotos(query: String) {
        progressCircular.visibility = View.VISIBLE
        val call = FlickerApi.create().search(query)
        call.enqueue(object : Callback<BaseResponseDo> {
            override fun onResponse(call: Call<BaseResponseDo>?, response: Response<BaseResponseDo>?) {
                if (response != null && response.isSuccessful) {
                    val responseDo = response.body()
                    responseDo?.photos?.photo?.let {
                        progressCircular.visibility = View.GONE
                        onPhotosFetched(it)
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponseDo>?, t: Throwable?) {

            }
        })
    }
}
