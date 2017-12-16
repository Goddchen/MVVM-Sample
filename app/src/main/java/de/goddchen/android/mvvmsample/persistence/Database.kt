package de.goddchen.android.mvvmsample.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import de.goddchen.android.mvvmsample.model.Chapter

@Database(entities = [Chapter::class], version = 1)
@TypeConverters(de.goddchen.android.mvvmsample.persistence.TypeConverters::class)
abstract class Database : RoomDatabase() {

    abstract fun chapterDao(): ChapterDao

}