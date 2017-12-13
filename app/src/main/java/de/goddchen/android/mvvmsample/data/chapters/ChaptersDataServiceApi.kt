package de.goddchen.android.mvvmsample.data.chapters

import de.goddchen.android.mvvmsample.api.ApiGenerator
import de.goddchen.android.mvvmsample.model.Chapter
import io.reactivex.Single

class ChaptersDataServiceApi : ChaptersDataService {
    override fun getChapters(): Single<List<Chapter>> {
        return ApiGenerator.generateApi().getChapters()
                .map { it.items }
    }
}