package com.example.xconspectus.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [SubjectDB::class, ThemeDB::class, ChapterDB::class, ConspectusDB::class],
    version = 1,
    exportSchema = true
)
abstract class XDatabase : RoomDatabase() {

    abstract fun subjectDao(): SubjectDao
    abstract fun themeDao(): ThemeDao
    abstract fun chapterDao(): ChapterDao
    abstract fun conspectusDao(): ConspectusDao

    companion object {
        @Volatile
        var INSTANCE: XDatabase? = null

        fun getDatabase(context: Context): XDatabase {
            val temporaryInstance = INSTANCE
            if (temporaryInstance != null) {
                return temporaryInstance
            }
            val instance =
                Room.databaseBuilder(context.applicationContext, XDatabase::class.java, "xdatabase")
                    .build()
            INSTANCE = instance
            return instance
        }
    }
}