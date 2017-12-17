package de.goddchen.android.mvvmsample.caching

import de.goddchen.android.mvvmsample.Application
import de.goddchen.android.mvvmsample.mvvm.model.Chapter
import io.reactivex.Flowable

class AndroidCacheProvider : CacheProvider {
    override fun setChapters(chapters: List<Chapter>) {
        Application.DATABASE?.chapterDao()?.addAll(chapters)
    }

    override fun getChapters(): Flowable<List<Chapter>> {
        return Application.DATABASE?.chapterDao()?.getAll() ?: Flowable.empty()
    }
}