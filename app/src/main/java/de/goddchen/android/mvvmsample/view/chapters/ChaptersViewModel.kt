package de.goddchen.android.mvvmsample.view.chapters

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.annimon.stream.ComparatorCompat
import com.annimon.stream.Stream
import com.annimon.stream.function.Function
import de.goddchen.android.mvvmsample.data.chapters.ChaptersDataService
import de.goddchen.android.mvvmsample.model.Chapter
import io.reactivex.Observable
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
        if (chapters.isEmpty()) {
            isLoading.postValue(true)
            dataService.getChapters()
                    .flatMapObservable { Observable.fromIterable(it) }
                    .toSortedList(ComparatorCompat.comparing(Function<Chapter, String> { it.name }))
                    .doFinally {
                        isLoading.postValue(false)
                    }
                    .subscribe({
                        chapters += it
                        applyFilter()
                    }, Timber::e)
        }
    }

    private fun applyFilter() {
        filteredChapters.postValue(Stream.of(chapters)
                .filter { it.name?.contains(filter as? CharSequence ?: "", true) ?: false }
                .toList())
    }

}