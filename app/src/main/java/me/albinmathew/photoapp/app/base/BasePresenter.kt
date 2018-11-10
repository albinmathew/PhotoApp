package me.albinmathew.photoapp.app.base

import android.support.annotation.CallSuper


interface BasePresenter<in PV : BasePresenterView> {

    fun onAttachView(presenterView: PV)

    fun onDetachView()
}


abstract class BasePresenterImpl<PV : BasePresenterView> : BasePresenter<PV> {

    @JvmField
    protected var presenterView: PV? = null

    @CallSuper
    override fun onAttachView(presenterView: PV) {
        this.presenterView = presenterView
        onViewAttached(presenterView)
    }

    @CallSuper
    open fun onViewAttached(presenterView: PV) {
        presenterView.initViews()
    }

    @CallSuper
    override fun onDetachView() {
        presenterView = null
    }
}


