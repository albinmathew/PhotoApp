package me.albinmathew.photoapp.app.base


interface BasePresenterView {

    fun initViews()

    fun showProgress()

    fun dismissProgress()
}