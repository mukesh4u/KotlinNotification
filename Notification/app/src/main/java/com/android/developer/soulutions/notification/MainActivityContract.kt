package com.android.developer.soulutions.notification


/**
 * Created by Mukesh on 5/10/2020.
 */
interface MainActivityContract {

    interface View {
        fun setPresenter(presenter: Presenter)
        fun createAndNotify()
    }

    interface Presenter {
        fun start()

        fun onNotificationBtnClicked()
    }

}