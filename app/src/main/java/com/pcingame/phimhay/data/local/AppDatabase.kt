package com.pcingame.phimhay.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pcingame.phimhay.data.entity.MovieEntity
import com.pcingame.phimhay.data.local.AppDatabase.Companion.DATABASE_VERSION
import com.pcingame.phimhay.data.local.dao.MovieDao

@Database(
    entities = [
        MovieEntity::class,
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        const val DATABASE_VERSION = 1
        private const val DB_NAME = "movie_db"

        fun build(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}
