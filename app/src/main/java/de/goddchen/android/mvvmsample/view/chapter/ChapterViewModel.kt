package de.goddchen.android.mvvmsample.view.chapter

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import de.goddchen.android.mvvmsample.model.Chapter
import java.util.*

class ChapterViewModel : ViewModel() {

    var chapter: Chapter? = null
        set(value) {
            field = value
            organizerCountText.postValue(String.format(Locale.getDefault(),
                    "Organizer Count: %d", chapter?.organizerIds?.size))
            addressText.postValue(String.format("%s, %s", chapter?.city, chapter?.country?.name))
        }

    val organizerCountText: MutableLiveData<String> = MutableLiveData()

    val addressText: MutableLiveData<String> = MutableLiveData()

}