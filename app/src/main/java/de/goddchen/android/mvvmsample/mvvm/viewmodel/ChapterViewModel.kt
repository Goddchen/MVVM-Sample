package de.goddchen.android.mvvmsample.mvvm.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import de.goddchen.android.mvvmsample.mvvm.model.Chapter
import de.goddchen.android.mvvmsample.mvvm.view.chapter.ChapterFormatProvider
import java.util.*

class ChapterViewModel(chapterFormatProvider: ChapterFormatProvider, val chapter: Chapter) : ViewModel() {

    val organizerCountText = String.format(Locale.getDefault(),
            chapterFormatProvider.formatOrganizerCount, chapter.organizerIds?.size)
    val addressText = String.format(Locale.getDefault(),
            chapterFormatProvider.formatAddress, chapter.city, chapter.country?.name)
    private val _addressClick = MutableLiveData<Chapter>()
    val addressClick: LiveData<Chapter> get() = _addressClick

    fun addressClicked() {
        _addressClick.postValue(chapter)
    }

}