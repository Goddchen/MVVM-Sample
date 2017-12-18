package de.goddchen.android.mvvmsample.mvvm.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.annimon.stream.Stream
import com.annimon.stream.function.Function
import de.goddchen.android.mvvmsample.caching.CacheProvider
import de.goddchen.android.mvvmsample.data.chapters.ChaptersDataService
import de.goddchen.android.mvvmsample.mvvm.model.Chapter
import io.reactivex.Flowable
import timber.log.Timber

class ChaptersViewModel(private val dataService: ChaptersDataService, private val cacheProvider: CacheProvider) : ViewModel() {

    private val chapters: MutableList<Chapter> = mutableListOf()

    val chapterClick = MutableLiveData<Chapter>()

    var filteredChapters: MutableLiveData<List<Chapter>>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
                loadChapters()
            }
            return field
        }

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    var filter: String? = null
        set(value) {
            field = value
            applyFilter()
        }

    private fun loadChapters() {
        isLoading.postValue(true)
        Flowable.merge(
                dataService.getChapters()
                        .doOnSuccess(cacheProvider::setChapters)
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
        filteredChapters?.postValue(Stream.of(chapters)
                .filter { it.name?.contains(filter as? CharSequence ?: "", true) ?: false }
                .sortBy(Function<Chapter, String> { it.name?.toLowerCase()?.trim() })
                .toList())
    }

    fun showChapter(chapter: Chapter) {
        chapterClick.postValue(chapter)
    }

}