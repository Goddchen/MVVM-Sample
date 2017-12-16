package de.goddchen.android.mvvmsample.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import de.goddchen.android.mvvmsample.mvvm.model.Chapter
import io.reactivex.Flowable

@Dao
interface ChapterDao {

    @Query("Select * from chapter")
    fun getAll(): Flowable<List<Chapter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(chapters: List<Chapter>)

}