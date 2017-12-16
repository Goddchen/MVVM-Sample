package de.goddchen.android.mvvmsample.api

import de.goddchen.android.mvvmsample.mvvm.model.ChapterResponse
import io.reactivex.Single
import retrofit2.http.GET

interface GDGxHubApi {

    @GET("chapters?perpage=1000")
    fun getChapters(): Single<ChapterResponse>

}