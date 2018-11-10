package me.albinmathew.photoapp.app.ui


import me.albinmathew.photoapp.app.api.BaseResponseDo
import me.albinmathew.photoapp.app.api.FlickerApi
import me.albinmathew.photoapp.app.base.BasePresenterImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosActivityPresenterImpl : BasePresenterImpl<PhotosActivityView>(), PhotosActivityPresenter {

    override fun searchPhotos(query: String) {
        presenterView?.showProgress()
        val call = FlickerApi.create().search(query)
        call.enqueue(object : Callback<BaseResponseDo> {
            override fun onResponse(call: Call<BaseResponseDo>?, response: Response<BaseResponseDo>?) {
                if (response != null && response.isSuccessful) {
                    val responseDo = response.body()
                    responseDo?.photos?.photo?.let {
                        presenterView?.dismissProgress()
                        presenterView?.onPhotosFetched(it)
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponseDo>?, t: Throwable?) {
            }
        })
    }

}