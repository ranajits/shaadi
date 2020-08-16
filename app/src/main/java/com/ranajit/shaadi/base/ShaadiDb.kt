package com.ranajit.shaadi.base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ranajit.shaadi.data.dao.MatchesDao
import com.ranajit.shaadi.model.MatchesModel

@Database(entities = [MatchesModel::class], version = 1, exportSchema = false)
abstract class ShaadiDb : RoomDatabase() {

    abstract fun matchesDao(): MatchesDao

    companion object {

        @Volatile
        private var INSTANCE: ShaadiDb? = null

        fun getDatabase(context: Context): ShaadiDb? {
            if (INSTANCE == null) {
                synchronized(ShaadiDb::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            ShaadiDb::class.java, "shaadi_db"
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }

}