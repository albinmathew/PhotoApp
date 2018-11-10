package me.albinmathew.photoapp.app.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class BaseResponseDo {
    @SerializedName("photos")
    lateinit var photos: PhotoResponseDo
}

class PhotoResponseDo {
    @SerializedName("photo")
    lateinit var photo: List<ImageData>
}

class ImageData : Serializable {

    @SerializedName("id")
    var id: String? = null

    @SerializedName("owner")
    var owner: String? = null

    @SerializedName("secret")
    var secret: String? = null

    @SerializedName("server")
    var server: String? = null

    @SerializedName("farm")
    var farm: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("ispublic")
    var isPublic: Int? = null

    @SerializedName("isfriend")
    var isFriend: Int? = null

    @SerializedName("isfamily")
    var isFamily: Int? = null
}