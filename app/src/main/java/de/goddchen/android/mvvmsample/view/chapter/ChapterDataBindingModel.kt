package de.goddchen.android.mvvmsample.view.chapter

import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import android.net.Uri
import de.goddchen.android.mvvmsample.model.Chapter
import java.util.*

class ChapterDataBindingModel {
    var chapter: Chapter? = null

    val organizerCountText: ObservableField<String> = ObservableField()

    val addressText: ObservableField<String> = ObservableField()

    fun addressClicked(context: Context) {
        context.startActivity(Intent(Intent.ACTION_VIEW,
                Uri.parse(String.format(Locale.US, "geo:%.6f,%.6f?q=%.6f,%.6f",
                        chapter?.geo?.lat, chapter?.geo?.lng,
                        chapter?.geo?.lat, chapter?.geo?.lng))))
    }
}