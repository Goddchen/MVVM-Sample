package de.goddchen.android.mvvmsample.mvvm.model

import java.io.Serializable

data class ChapterResponse(
        val count: Int,
        val pages: Int,
        val page: Int,
        val perpage: Int,
        val items: List<Chapter>
) : Serializable