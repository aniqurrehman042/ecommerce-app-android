package com.sahoolatkar.sahoolatkar.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sahoolatkar.sahoolatkar.dao.NotificationDao
import com.sahoolatkar.sahoolatkar.models.Notification

@Database(entities = [Notification::class], version = 1)
abstract class SahoolatkarDB : RoomDatabase() {

    abstract fun notificationDao() : NotificationDao

}