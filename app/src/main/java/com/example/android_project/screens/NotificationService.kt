package com.example.android_project.screens

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.android_project.R
import kotlin.random.Random

class NotificationService(
    private val context:Context
){
    private val notificationManager=context.getSystemService(NotificationManager::class.java)
    @RequiresApi(Build.VERSION_CODES.O)
    fun showBasicNotification(){
        val notification= Notification.Builder(context,"test")
            .setContentTitle("New group created")
            .setContentText("You have been invited to a group")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }
    /*
    fun showExpandableNotification(){
        val notification=NotificationCompat.Builder(context,"water_notification")
            .setContentTitle("Water Reminder")
            .setContentText("Time to drink a glass of water")
            .setSmallIcon(R.drawable.water_icon)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .setStyle(
                NotificationCompat
                    .BigPictureStyle()
                    .bigPicture(
                        context.bitmapFromResource(
                            R.drawable.img_1
                        )
                    )
            )
            .build()
        notificationManager.notify(Random.nextInt(),notification)
    }

    private fun Context.bitmapFromResource(
        @DrawableRes resId:Int
    )= BitmapFactory.decodeResource(
        resources,
        resId
    )

     */
}