package com.sahoolatkar.sahoolatkar.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sahoolatkar.sahoolatkar.models.Notification

@Dao
interface NotificationDao {
    @Query("SELECT * FROM notification ORDER BY receive_time DESC")
    fun getAll(): List<Notification>

    @Insert
    fun insertAll(vararg users: Notification)


}