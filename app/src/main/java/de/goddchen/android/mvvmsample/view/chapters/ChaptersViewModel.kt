package de.goddchen.android.mvvmsample.view.chapters

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import com.annimon.stream.ComparatorCompat
import com.annimon.stream.Stream
import com.annimon.stream.function.Function
import de.goddchen.android.mvvmsample.BR
import de.goddchen.android.mvvmsample.R
import de.goddchen.android.mvvmsample.data.chapters.ChaptersDataService
import de.goddchen.android.mvvmsample.model.Chapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import me.tatarka.bindingcollectionadapter2.OnItemBind
import timber.log.Timber

class ChaptersViewModel : ViewModel() {

    private val chapters: MutableList<Chapter> = mutableListOf()

    val filteredChapters: ObservableList<Chapter> = ObservableArrayList()

    val isLoading: ObservableBoolean = ObservableBoolean(false)

    val clickedChapter: MutableLiveData<Chapter> = MutableLiveData()

    var filter: String? = null
        set(value) {
            field = value
            applyFilter()
        }

    val itemBinding: OnItemBind<Chapter> = OnItemBind { itemBinding, _, _ ->
        itemBinding.set(BR.chapter, R.layout.item_chapter)
        itemBinding.bindExtra(BR.clickListener, object : OnClickListener {
            override fun onChapterClicked(chapter: Chapter) {
                clickedChapter.value = chapter
            }
        })
    }

    fun loadChapters(dataService: ChaptersDataService) {
        if (chapters.isEmpty()) {
            isLoading.set(true)
            dataService.getChapters()
                    .flatMapObservable { Observable.fromIterable(it) }
                    .toSortedList(ComparatorCompat.comparing(Function<Chapter, String> { it.name }))
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally {
                        isLoading.set(false)
                    }
                    .subscribe({
                        chapters += it
                        applyFilter()
                    }, Timber::e)
        }
    }

    private fun applyFilter() {
        filteredChapters.clear()
        filteredChapters.addAll(Stream.of(chapters)
                .filter { it.name?.contains(filter as? CharSequence ?: "", true) ?: false }
                .toList())
    }

}