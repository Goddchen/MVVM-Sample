package de.goddchen.android.mvvmsample.view.chapters

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import de.goddchen.android.mvvmsample.BR
import de.goddchen.android.mvvmsample.R
import de.goddchen.android.mvvmsample.api.ApiGenerator
import de.goddchen.android.mvvmsample.model.Chapter
import io.reactivex.android.schedulers.AndroidSchedulers
import me.tatarka.bindingcollectionadapter2.OnItemBind
import timber.log.Timber

class ChaptersViewModel : ViewModel() {

    val chapters: ObservableList<Chapter> = ObservableArrayList()

    val isLoading: ObservableBoolean = ObservableBoolean(false)

    val clickedChapter: MutableLiveData<Chapter> = MutableLiveData()

    val itemBinding: OnItemBind<Chapter> = OnItemBind { itemBinding, _, _ ->
        itemBinding.set(BR.chapter, R.layout.item_chapter)
        itemBinding.bindExtra(BR.clickListener, object : OnClickListener {
            override fun onChapterClicked(chapter: Chapter) {
                clickedChapter.value = chapter
            }
        })
    }

    fun loadChapters() {
        if (chapters.isEmpty()) {
            isLoading.set(true)
            ApiGenerator.generateApi().getChapters()
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally { isLoading.set(false) }
                    .subscribe({ chapters.addAll(it.items) }, Timber::e)
        }
    }

}