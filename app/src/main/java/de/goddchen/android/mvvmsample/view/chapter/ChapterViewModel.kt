package de.goddchen.android.mvvmsample.view.chapter

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import de.goddchen.android.mvvmsample.model.Chapter
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