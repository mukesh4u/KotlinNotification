package com.android.developer.soulutions.notification


/**
 * Created by Mukesh on 5/10/2020.
 * mukesh.yadav
 */
class MainActivityPresenter(private val view: MainActivityContract.View) : MainActivityContract.Presenter {

    init {
        start()
    }
    override fun start() {
        view.setPresenter(this)
    }

    override fun onNotificationBtnClicked() {
        view.createAndNotify()
    }
}