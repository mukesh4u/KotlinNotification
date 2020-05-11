package com.android.developer.soulutions.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RemoteViews
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityContract.View {

    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationManager: NotificationManager
    lateinit var builder: Notification.Builder
    private lateinit var presenter: MainActivityContract.Presenter
    private val channelId = "chanelId 1"
    private val description = "Hi android devloper solutions,hi mukesh"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainActivityPresenter(this)
        initView()

    }

    private fun initView() {
        btn_send_notification.setOnClickListener {
            //it is a class to notify the user of events that happen.
            // This is how you tell the user that something has happened in the
            notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            presenter.onNotificationBtnClicked()
        }
    }

    override fun setPresenter(presenter: MainActivityContract.Presenter) {
        this.presenter = presenter
    }

    override fun createAndNotify() {
        val intent = Intent(this,NotificationActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        //RemoteViews are used to use the content of
        // some different layout apart from the current activity layout
        val contentView = RemoteViews(packageName,R.layout.activity_notification)
        //checking if android version is greater than oreo(API 26) or not
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(
                channelId,description,NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this,channelId)
                .setContentTitle("Notifications Example") //set title of notification
                .setContentText("This is a notification message")//this is notification message
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(
                    BitmapFactory.decodeResource(this.resources,
                    R.drawable.ic_launcher_background))
                .setContentIntent(pendingIntent)
        }else{

            builder = Notification.Builder(this)
                .setContentTitle("Notifications Example") //set title of notification
                .setContentText("This is a notification message")//this is notification message
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources,
                    R.drawable.ic_launcher_background))
                .setContentIntent(pendingIntent)
        }
        notificationManager.notify(1234,builder.build())
    }
}
