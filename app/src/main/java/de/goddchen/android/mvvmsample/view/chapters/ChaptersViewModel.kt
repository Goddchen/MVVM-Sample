package de.goddchen.android.mvvmsample.view.chapters

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.annimon.stream.Stream
import de.goddchen.android.mvvmsample.Application
import de.goddchen.android.mvvmsample.data.chapters.ChaptersDataService
import de.goddchen.android.mvvmsample.model.Chapter
import io.reactivex.Flowable
import timber.log.Timber

class ChaptersViewModel : ViewModel() {

    private val chapters: MutableList<Chapter> = mutableListOf()

    val filteredChapters: MutableLiveData<List<Chapter>> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    var filter: String? = null
        set(value) {
            field = value
            applyFilter()
        }

    fun loadChapters(dataService: ChaptersDataService) {
        Flowable.merge(
                dataService.getChapters()
                        .doOnSuccess { Application.DATABASE?.chapterDao()?.addAll(it) }
                        .toFlowable(),
                Application.DATABASE?.chapterDao()?.getAll())
                .subscribe({
                    chapters.clear()
                    chapters.addAll(it)
                    applyFilter()
                }, Timber::e)
    }

    private fun applyFilter() {
        filteredChapters.postValue(Stream.of(chapters)
                .filter { it.name?.contains(filter as? CharSequence ?: "", true) ?: false }
                .toList())
    }

}