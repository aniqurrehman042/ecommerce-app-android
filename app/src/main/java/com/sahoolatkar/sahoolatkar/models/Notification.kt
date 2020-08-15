package com.sahoolatkar.sahoolatkar.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sahoolatkar.sahoolatkar.utils.DateUtils
import java.util.*

@Entity
data class Notification(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "receive_time") var time: String = DateUtils.dateToTimeString(Calendar.getInstance().time)!!,
    @ColumnInfo(name = "type") var type: String = "default"
)