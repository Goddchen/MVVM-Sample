package de.goddchen.android.mvvmsample.mvvm.viewmodel

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import com.annimon.stream.Stream
import com.annimon.stream.function.Function
import de.goddchen.android.mvvmsample.Application
import de.goddchen.android.mvvmsample.caching.CacheProvider
import de.goddchen.android.mvvmsample.data.chapters.ChaptersDataService
import de.goddchen.android.mvvmsample.mvvm.model.Chapter
import de.goddchen.android.mvvmsample.mvvm.view.chapter.ChapterActivity
import io.reactivex.Flowable
import timber.log.Timber

class ChaptersViewModel(application: android.app.Application) : AndroidViewModel(application) {

    private val chapters: MutableList<Chapter> = mutableListOf()

    val filteredChapters: MutableLiveData<List<Chapter>> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    var filter: String? = null
        set(value) {
            field = value
            applyFilter()
        }

    fun loadChapters(dataService: ChaptersDataService, cacheProvider: CacheProvider) {
        isLoading.postValue(true)
        Flowable.merge(
                dataService.getChapters()
                        .doOnSuccess { Application.DATABASE?.chapterDao()?.addAll(it) }
                        .toFlowable(),
                cacheProvider.getChapters())
                .doOnNext { isLoading.postValue(false) }
                .doFinally { isLoading.postValue(false) }
                .subscribe({
                    chapters.clear()
                    chapters.addAll(it)
                    applyFilter()
                }, Timber::e)
    }

    private fun applyFilter() {
        filteredChapters.postValue(Stream.of(chapters)
                .filter { it.name?.contains(filter as? CharSequence ?: "", true) ?: false }
                .sortBy(Function<Chapter, String> { it.name?.trim() })
                .toList())
    }

    fun showChapter(chapter: Chapter) =
            getApplication<Application>()
                    .startActivity(
                            Intent(getApplication(), ChapterActivity::class.java)
                                    .putExtra(ChapterActivity.EXTRA_CHAPTER, chapter))

}