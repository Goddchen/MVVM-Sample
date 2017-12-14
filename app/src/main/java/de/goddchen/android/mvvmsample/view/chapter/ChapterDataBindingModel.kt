package de.goddchen.android.mvvmsample.view.chapter

import android.databinding.ObservableField
import de.goddchen.android.mvvmsample.model.Chapter

class ChapterDataBindingModel {
    var chapter: Chapter? = null

    val organizerCountText: ObservableField<String> = ObservableField()

    val addressText: ObservableField<String> = ObservableField()
}