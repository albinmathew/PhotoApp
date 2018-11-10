package me.albinmathew.photoapp.app.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
abstract class BaseActivity<in PV : BasePresenterView,
        Presenter : BasePresenter<PV>> : AppCompatActivity(), BasePresenterView {

    @Inject
    lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(getLayout())
        super.onCreate(savedInstanceState)
    }

    abstract fun getLayout(): Int

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        presenter.onAttachView(this as PV)
    }

    @CallSuper
    override fun initViews() {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetachView()
    }
}