package de.goddchen.android.mvvmsample.data.chapters

import de.goddchen.android.mvvmsample.model.Chapter
import io.reactivex.Single

class ChaptersDataServiceMock : ChaptersDataService {
    override fun getChapters(): Single<List<Chapter>> {
        return Single.just(listOf(
                Chapter(null, null, null, null, null, null, null, null, null, "GDG Bodensee", null, null, null),
                Chapter(null, null, null, null, null, null, null, null, null, "GDG Zürich", null, null, null)
        ))
    }
}