package de.goddchen.android.mvvmsample.caching

import de.goddchen.android.mvvmsample.mvvm.model.Chapter
import io.reactivex.Flowable

interface CacheProvider {

    fun getChapters(): Flowable<List<Chapter>>

}