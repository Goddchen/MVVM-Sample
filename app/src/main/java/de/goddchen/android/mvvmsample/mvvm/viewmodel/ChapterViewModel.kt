package de.goddchen.android.mvvmsample.mvvm.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import de.goddchen.android.mvvmsample.mvvm.model.Chapter
import de.goddchen.android.mvvmsample.mvvm.view.chapter.ChapterFormatProvider
import java.util.*

class ChapterViewModel : ViewModel() {

    val organizerCountText: MutableLiveData<String> = MutableLiveData()

    val addressText: MutableLiveData<String> = MutableLiveData()

    fun init(chapter: Chapter, chapterFormatProvider: ChapterFormatProvider) {
        organizerCountText.postValue(String.format(Locale.getDefault(),
                chapterFormatProvider.formatOrganizerCount, chapter.organizerIds?.size))
        addressText.postValue(String.format(Locale.getDefault(),
                chapterFormatProvider.formatAddress, chapter.city, chapter.country?.name))
    }

}