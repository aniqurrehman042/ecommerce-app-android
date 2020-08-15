package com.sahoolatkar.sahoolatkar.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.sahoolatkar.sahoolatkar.R
import com.sahoolatkar.sahoolatkar.models.Notification
import com.sahoolatkar.sahoolatkar.ui.MainActivity
import com.sahoolatkar.sahoolatkar.utils.DBUtils
import kotlin.random.Random

class SahoolatkarNotificationsService : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        if (p0.notification != null) {
            val title = p0.notification!!.title
            val message = p0.notification!!.body
            DBUtils.getDBInstance().notificationDao()
                .insertAll(Notification(title = title!!, description = message!!))

            sendNotification(title, message)

        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendNotification(title: String, message: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val builder = NotificationCompat.Builder(this, "1234")
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_logo_icon)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)

        createNotificationChannel()

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(Random(200).nextInt(), builder.build())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
            val channel = NotificationChannel(
                "1234",
                "Notification Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }
}