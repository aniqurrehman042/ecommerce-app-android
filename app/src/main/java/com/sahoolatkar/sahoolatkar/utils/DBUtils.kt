package com.sahoolatkar.sahoolatkar.utils

import android.content.Context
import androidx.room.Room
import com.sahoolatkar.sahoolatkar.application.GlobalApplication
import com.sahoolatkar.sahoolatkar.db.SahoolatkarDB

class DBUtils {

    companion object {

        private var sahoolatkarDB: SahoolatkarDB? = null

        fun getDBInstance() : SahoolatkarDB {
            return when {
                sahoolatkarDB != null -> {
                    sahoolatkarDB!!
                }
                else -> {
                    sahoolatkarDB = Room.databaseBuilder(
                        GlobalApplication.getAppContext()!!,
                        SahoolatkarDB::class.java, "SahoolatkarDB"
                    ).build()
                    sahoolatkarDB!!
            }
        }

        }

    }

}