package de.goddchen.android.mvvmsample.view.chapter

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import de.goddchen.android.mvvmsample.model.Chapter
import java.util.*

class ChapterViewModel : ViewModel() {

    var chapter: Chapter? = null
        set(value) {
            field = value
            organizerCountText.set(String.format(Locale.getDefault(),
                    "Organizer Count: %d", chapter?.organizerIds?.size))
        }

    var organizerCountText: ObservableField<String> = ObservableField()

}