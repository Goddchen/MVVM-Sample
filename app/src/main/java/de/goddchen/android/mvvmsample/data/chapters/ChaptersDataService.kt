package de.goddchen.android.mvvmsample.data.chapters

import de.goddchen.android.mvvmsample.mvvm.model.Chapter
import io.reactivex.Single

interface ChaptersDataService {

    fun getChapters(): Single<List<Chapter>>

}