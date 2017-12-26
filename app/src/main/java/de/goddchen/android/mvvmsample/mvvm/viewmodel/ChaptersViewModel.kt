package de.goddchen.android.mvvmsample.mvvm.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import com.annimon.stream.Stream
import com.annimon.stream.function.Function
import de.goddchen.android.mvvmsample.BR
import de.goddchen.android.mvvmsample.R
import de.goddchen.android.mvvmsample.caching.CacheProvider
import de.goddchen.android.mvvmsample.data.chapters.ChaptersDataService
import de.goddchen.android.mvvmsample.mvvm.model.Chapter
import io.reactivex.Flowable
import me.tatarka.bindingcollectionadapter2.OnItemBind
import timber.log.Timber

class ChaptersViewModel(private val dataService: ChaptersDataService, private val cacheProvider: CacheProvider) : ViewModel() {

    private val _chapters: MutableList<Chapter> = mutableListOf()

    private val _chapterClick = MutableLiveData<Chapter>()
    val chapterClick: LiveData<Chapter> get() = _chapterClick

    val itemBinding = OnItemBind<Chapter> { itemBinding, _, _ ->
        itemBinding.set(BR.chapter, R.layout.item_chapter)
        itemBinding.bindExtra(BR.model, this)
    }

    private var _filteredChapters: MutableLiveData<List<Chapter>>? = null
    val filteredChapters: LiveData<List<Chapter>>
        get() {
            if (_filteredChapters == null) {
                _filteredChapters = MutableLiveData()
                loadChapters()
            }
            return _filteredChapters ?: MutableLiveData()
        }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    val filter = MutableLiveData<String>()
    private val filterObserver = Observer<String> { applyFilter() }

    init {
        filter.observeForever(filterObserver)
    }

    override fun onCleared() {
        super.onCleared()
        filter.removeObserver(filterObserver)
    }

    @SuppressLint("CheckResult")
    private fun loadChapters() {
        _isLoading.postValue(true)
        Flowable.merge(
                dataService.getChapters()
                        .doOnSuccess(cacheProvider::setChapters)
                        .toFlowable(),
                cacheProvider.getChapters())
                .doOnNext { _isLoading.postValue(false) }
                .doFinally { _isLoading.postValue(false) }
                .subscribe({
                    _chapters.clear()
                    _chapters.addAll(it)
                    applyFilter()
                }, Timber::e)
    }

    private fun applyFilter() {
        _filteredChapters?.postValue(Stream.of(_chapters)
                .filter { it.name?.contains(filter.value as? CharSequence ?: "", true) ?: false }
                .sortBy(Function<Chapter, String> { it.name?.toLowerCase()?.trim() })
                .toList())
    }

    fun showChapter(chapter: Chapter) {
        _chapterClick.postValue(chapter)
    }

}